package com.bigzhao.decoder;

import com.bigzhao.antlr.zfdLexer;
import com.bigzhao.antlr.zfdParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * Created by Roy on 15-9-19.
 */
public class Expression {
    private Code parent;
    private TerminalNode terminalNode;
    private zfdParser.ExpressionContext c;
    public Expression(Code parent,zfdParser.ExpressionContext c){
        this.parent=parent;
        this.c=c;
    }

    public Expression(Code parent,TerminalNode node){
        this.terminalNode=node;
    }

    public int resolveInt(Object v){
        return ResolveInt(eval(v));
    }

    public double resolveNum(Object v){
        return ResolveNum(eval(v));
    }

    public int resolveBool(Object v){
        return ResolveBool(eval(v));
    }

    public static Object resolveRef(Code code,zfdParser.RefContext c){
        zfdParser.RefContext ref=c.ref();
        zfdParser.RefSegContext seg=c.refSeg();
        if (ref!=null) code=(Code)resolveRef(code,ref);
        int num= seg.NUMBER()!=null?ResolveInt(seg.NUMBER().getText()):0;
        return code.getFieldValue(seg.NAME().getText(), num);
    }

    public static int ResolveInt(Object v){
        if (v==null) return 0;
        if (v instanceof Byte) return 0x000000ff&(Byte)v;
        if (v instanceof Number) return ((Number) v).intValue();
        String s=v.toString();
        if (s.startsWith("0x")) return Integer.parseInt(s.substring(2),16);
        return (int)Double.parseDouble(s);
    }

    public static double ResolveNum(Object v){
        if (v==null) return 0;
        if (v instanceof Byte) return 0x000000ff&(Byte)v;
        if (v instanceof Number) return ((Number) v).doubleValue();
        String s=v.toString();
        if (s.startsWith("0x")) return Integer.parseInt(s.substring(2), 16);
        return Double.parseDouble(s);
    }

    public static int ResolveBool(Object v){
        try {
            double d = ResolveNum(v);
            return d==0?0:1;
        }catch (NumberFormatException e){
            return 1;
        }
    }

    private Object eval(zfdParser.ValueExpressionContext c){
        zfdParser.ValueContext v=c.value();
        ParseTree n=c.value().getChild(0);
        if (n instanceof TerminalNode) return Code.getValue((TerminalNode) n);
        return resolveRef(parent, v.ref());
    }

    private double eval(zfdParser.UnaryPlusExpressionContext c){
        Object o=eval(c.expression());
        return resolveNum(o);
    }

    private double eval(zfdParser.UnaryMinusExpressionContext c){
        Object o=eval(c.expression());
        return -resolveNum(o);
    }

    private int eval(zfdParser.BitNotExpressionContext c){
        return ~resolveInt(eval(c.expression()));
    }

    private int eval(zfdParser.LogicalNotExpressionContext c){
        return 1-resolveBool(eval(c.expression()));
    }
    private double eval(zfdParser.MultiplicativeExpressionContext c){
        String op=c.getChild(1).getText();
        if ("*".equals(op)) return resolveNum(c.expression(0))*resolveNum(c.expression(1));
        else if ("/".equals(op)) return resolveNum(c.expression(0))/resolveNum(c.expression(1));
        else if ("%".equals(op)) return resolveNum(c.expression(0))%resolveNum(c.expression(1));
        throw new IllegalArgumentException("operator "+op);
    }

    private double eval(zfdParser.AdditiveExpressionContext c){
        String op=c.getChild(1).getText();
        if ("+".equals(op)) return resolveNum(c.expression(0))+resolveNum(c.expression(1));
        else if ("-".equals(op)) return resolveNum(c.expression(0))-resolveNum(c.expression(1));
        throw new IllegalArgumentException("operator "+op);
    }

    private int eval(zfdParser.BitShiftExpressionContext c){
        String op=c.getChild(1).getText();
        if ("<<".equals(op)) return resolveInt(c.expression(0))<<resolveInt(c.expression(1));
        else if (">>".equals(op)) return resolveInt(c.expression(0))>>resolveInt(c.expression(1));
        throw new IllegalArgumentException("operator "+op);
    }

    private int eval(zfdParser.RelationalExpressionContext c){
        String op=c.getChild(1).getText();
        if (">".equals(op)) return resolveNum(c.expression(0))>resolveNum(c.expression(1))?1:0;
        else if ("<".equals(op)) return resolveNum(c.expression(0))<resolveNum(c.expression(1))?1:0;
        else if (">=".equals(op)) return resolveNum(c.expression(0))>=resolveNum(c.expression(1))?1:0;
        else if ("<=".equals(op)) return resolveNum(c.expression(0))<=resolveNum(c.expression(1))?1:0;
        throw new IllegalArgumentException("operator "+op);
    }

    private int eval(zfdParser.EqualityExpressionContext c){
        String op=c.getChild(1).getText();
        if ("==".equals(op)) return resolveNum(c.expression(0))==resolveNum(c.expression(1))?1:0;
        else if ("!=".equals(op)) return resolveNum(c.expression(0))!=resolveNum(c.expression(1))?1:0;
        throw new IllegalArgumentException("operator "+op);
    }

    private int eval(zfdParser.BitAndExpressionContext c){
        return resolveInt(c.expression(0))&resolveInt(c.expression(1));
    }

    private int eval(zfdParser.BitXOrExpressionContext c){
        return resolveInt(c.expression(0))^resolveInt(c.expression(1));
    }

    private int eval(zfdParser.BitOrExpressionContext c){
        return resolveInt(c.expression(0))|resolveInt(c.expression(1));
    }

    private int eval(zfdParser.LogicalAndExpressionContext c){
        return (resolveBool(c.expression(0))&resolveBool(c.expression(1)))==1?1:0;
    }

    private int eval(zfdParser.LogicalOrExpressionContext c){
        return (resolveBool(c.expression(0))|resolveBool(c.expression(1)))==1?1:0;
    }

    private Object eval(zfdParser.TernaryExpressionContext c){
        int b=resolveBool(eval(c.expression(0)));
        if (b==1) return eval(c.expression(1));
        else return eval(c.expression(2));
    }

    private Object eval(zfdParser.ParenthesizedExpressionContext c){
        return eval(c.expression());
    }

    private Object eval(Object c){
        if (c instanceof String) return c;
        if (c instanceof Number) return c;
        if (c instanceof zfdParser.ValueExpressionContext) return eval((zfdParser.ValueExpressionContext) c);
        else if (c instanceof zfdParser.UnaryPlusExpressionContext) return eval((zfdParser.UnaryPlusExpressionContext)c);
        else if (c instanceof zfdParser.UnaryMinusExpressionContext) return eval((zfdParser.UnaryMinusExpressionContext)c);
        else if (c instanceof zfdParser.BitNotExpressionContext) return eval((zfdParser.BitNotExpressionContext)c);
        else if (c instanceof zfdParser.LogicalNotExpressionContext) return eval((zfdParser.LogicalNotExpressionContext)c);
        else if (c instanceof zfdParser.MultiplicativeExpressionContext) return eval((zfdParser.MultiplicativeExpressionContext)c);
        else if (c instanceof zfdParser.AdditiveExpressionContext) return eval((zfdParser.AdditiveExpressionContext)c);
        else if (c instanceof zfdParser.BitShiftExpressionContext) return eval((zfdParser.BitShiftExpressionContext)c);
        else if (c instanceof zfdParser.RelationalExpressionContext) return eval((zfdParser.RelationalExpressionContext)c);
        else if (c instanceof zfdParser.EqualityExpressionContext) return eval((zfdParser.EqualityExpressionContext)c);
        else if (c instanceof zfdParser.BitAndExpressionContext) return eval((zfdParser.BitAndExpressionContext)c);
        else if (c instanceof zfdParser.BitXOrExpressionContext) return eval((zfdParser.BitXOrExpressionContext)c);
        else if (c instanceof zfdParser.BitOrExpressionContext) return eval((zfdParser.BitOrExpressionContext)c);
        else if (c instanceof zfdParser.LogicalAndExpressionContext) return eval((zfdParser.LogicalAndExpressionContext)c);
        else if (c instanceof zfdParser.LogicalOrExpressionContext) return eval((zfdParser.LogicalOrExpressionContext)c);
        else if (c instanceof zfdParser.TernaryExpressionContext) return eval((zfdParser.TernaryExpressionContext)c);
        else if (c instanceof zfdParser.ParenthesizedExpressionContext) return eval((zfdParser.ParenthesizedExpressionContext)c);
        else throw new RuntimeException("unknown expression"+c);
    }

    private Object evalTerminal(){
        if (terminalNode.getSymbol().getType()== zfdLexer.INFINITE){
            return Integer.MAX_VALUE;
        }
        return terminalNode.getText();
    }

    public Object evaluate(){
        if (c==null) return evalTerminal();
        return eval(c);
    }
}
