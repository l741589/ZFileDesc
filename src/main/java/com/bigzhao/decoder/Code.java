package com.bigzhao.decoder;

import com.bigzhao.antlr.zfdLexer;
import com.bigzhao.antlr.zfdParser;
import com.bigzhao.decoder.type.IType;
import com.bigzhao.decoder.type.Types;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Roy on 15-9-19.
 */
public class Code implements IType<Code> {
    /**parse time*/
    private final String name;
    private final Code parent;
    private final zfdParser.CodeContext context;
    private List<zfdParser.ElementContext> elements;
    private HashMap<String, String> config;
    private HashMap<String, IType> types = new HashMap<String, IType>();
    private LinkedList<Field> fields = new LinkedList<Field>();

    /**Runtime*/
    private HashMap<String,Field> fieldValues;



    @SuppressWarnings("unchecked")
    public Code(Code parent, String name, zfdParser.CodeContext c) {
        this.parent = parent;
        if (parent!=null) parent.types.put(name,this);
        this.name = name;
        this.context=c;
        this.elements = c.element();
        config = parent != null ? (HashMap<String, String>) parent.config.clone() : new HashMap<String, String>();
        for (zfdParser.ElementContext e : elements) {
            ParseTree t = e.getChild(0);
            if (t instanceof zfdParser.ConfigDefContext) readConfig((zfdParser.ConfigDefContext) t);
            else if (t instanceof zfdParser.StructDefContext) readStruct((zfdParser.StructDefContext) t);
            else if (t instanceof zfdParser.TypeDefContext) readType((zfdParser.TypeDefContext) t);
            else if (t instanceof zfdParser.FieldDefContext) readField((zfdParser.FieldDefContext) t);
        }
    }

    public zfdParser.CodeContext getContext() {
        return context;
    }

    public Object getFieldValue(String name){
        return getFieldValue(name, 0);
    }

    public Object getFieldValue(String name, int index) {
        Field f= fieldValues.get(name);
        if (f!=null) return f.getValue()[index];
        if (parent!=null) return parent.getFieldValue(name, index);
        return null;
    }

    public static Object getValue(TerminalNode node) {
        if (node == null) return null;
        if (node.getSymbol().getType() == zfdLexer.STRINGLIT) {
            String s = node.getText();
            return s.substring(1, s.length() - 1).replace("\"\"", "\"");
        } else if (node.getSymbol().getType() == zfdLexer.NUMBER) {
            String s = node.getText();
            return Expression.ResolveInt(s);
        } else {
            return node.getText();
        }
    }

    private void readConfig(zfdParser.ConfigDefContext c) {
        if (c == null) return;
        if (c instanceof zfdParser.ConfigDefSingleLineContext) {
            zfdParser.ConfigDefLineContext line = ((zfdParser.ConfigDefSingleLineContext) c).configDefLine();
            config.put(line.NAME().getText(), getValue((TerminalNode) line.getChild(2)).toString());
        } else if (c instanceof zfdParser.ConfigDefBlockContext) {
            List<zfdParser.ConfigDefLineContext> lines = ((zfdParser.ConfigDefBlockContext) c).configDefLine();
            for (zfdParser.ConfigDefLineContext line : lines) {
                config.put(line.NAME().getText(), getValue((TerminalNode) line.getChild(2)).toString());
            }
        }
    }

    private void readStruct(zfdParser.StructDefContext c) {
        String name = c.NAME().getText();
        new Code(this, name, c.structBody().code());
    }

    private void readType(zfdParser.TypeDefContext c) {
        try {
            Class<?> cls = Class.forName(c.className().getText());
            types.put(c.NAME().getText(), (IType) cls.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    private void readField(zfdParser.FieldDefContext c) {
        Field f=new Field(this,c);
        fields.add(f);
    }
    public void addSysTypes(){
        types.put("int8",new Types.int8());
        types.put("int16",new Types.int16());
        types.put("int32",new Types.int32());
        types.put("leb",new Types.leb());
        types.put("string", new Types.string());
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Code getValue() {
        return this;
    }

    public IType getType(String name){
        Code c=this;
        IType t;
        while(true){
            t=c.types.get(name);
            if (t!=null||c.parent==null) return t;
            c=c.parent;
        }
    }

    @Override
    public void read(PullReader reader, Object[] args, Map<String, String> config) throws IOException {
        fieldValues =new HashMap<String, Field>();
        PullReader r=reader;
        if (args!=null&&args.length>0){
            byte[] bs=reader.pull(Expression.ResolveInt(args[0]));
            r=new PullReader(bs);
        }
        try {
            for (Field f : fields) {
                fieldValues.put(f.getName(), f);
                f.read(r, this.config);
            }
        }catch (EndOfBlockException e){
            System.out.println("End Of "+this+" Reached");
            if (r==reader) throw e;
        }
    }

    @Override
    public String toString() {
        return "Code: "+getName();
    }

    private int depth=-1;
    public int depth(){
        if (depth>=0) return depth;
        if (parent==null) return depth=0;
        return depth=parent.depth()+1;
    }
}
