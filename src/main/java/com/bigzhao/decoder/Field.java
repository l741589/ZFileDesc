package com.bigzhao.decoder;

import com.bigzhao.antlr.zfdParser;
import com.bigzhao.decoder.type.IType;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Roy on 15-9-19.
 */
public class Field {
    private String name;
    private String type;
    private Expression count;
    private LinkedList<Expression> args=new LinkedList<Expression>();
    private zfdParser.FieldDefContext context;
    private Code parent;

    private Object[] value;

    public Field(Code parent,zfdParser.FieldDefContext c){
        this.parent=parent;
        context=c;
        name=c.getChild(0).getText();
        ParseTree t=c.getChild(1);
        if (t instanceof TerminalNode) type=c.getChild(1).getText();
        else {
            type=Decoder.newName();
            new Code(parent, type, c.structBody().code());
        }
        int argEnd=c.getChildCount();
        if (argEnd>2&&c.getChild(argEnd-1) instanceof TerminalNode){
            ParseTree tt=c.getChild(argEnd-2);
            if (tt instanceof TerminalNode) count=new Expression(parent,(TerminalNode)tt);
            else count= new Expression(parent,(zfdParser.ExpressionContext)tt);
            argEnd-=3;
        }
        for (int i=2;i<argEnd;++i){
            args.add(new Expression(parent,(zfdParser.ExpressionContext) c.getChild(i)));
        }
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void read(PullReader reader, Map<String, String> config) throws IOException{
        int cnt=count==null?1:Expression.ResolveInt(count.evaluate());
        if (cnt==0) {
            value=new Object[0];
            return;
        }
        IType t=parent.getType(getType());
        if (t==null){
            System.err.println("Invalid Type: "+getType()+" "+context.getStart().getLine());
        }
        if (t instanceof Code){
            System.out.println(StringUtils.join(new Object[parent.depth()+1],'\t')+getName() +" : " +t.getName()+" {");
        }
        Object[] args=new Object[this.args.size()];
        int i=0;
        for (Expression e:this.args) args[i++]=e.evaluate();
        LinkedList<Object> values=new LinkedList<Object>();
        try {
            for (i = 0; i < cnt; ++i) {
                t.read(reader, args, config);
                values.add(t.getValue());
            }
        }finally {
            value=values.toArray();
            if (!(t instanceof Code)) System.out.println(StringUtils.join(new Object[parent.depth()+1],'\t')+getName() + ":" + StringUtils.join(getValue(), ','));
            else System.out.println(StringUtils.join(new Object[parent.depth()+1],'\t')+"}");
        }
    }

    public Object[] getValue() {
        return value;
    }
}
