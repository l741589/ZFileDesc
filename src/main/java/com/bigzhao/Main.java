package com.bigzhao;

import com.bigzhao.antlr.zfdLexer;
import com.bigzhao.antlr.zfdParser;
import com.bigzhao.decoder.Decoder;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try {
            CharStream cs=new ANTLRFileStream("E:\\IntellijWS\\FileDecoder\\src\\main\\resources\\a.zfd");
            zfdLexer l=new zfdLexer(cs);
            TokenStream ts = new CommonTokenStream(l);
            zfdParser p=new zfdParser(ts);
            //printTree(p.code(),0);

            Decoder.decode("E:\\IntellijWS\\FileDecoder\\src\\main\\resources\\a.zfd","E:\\IntellijWS\\FileDecoder\\src\\main\\resources\\qby.mid");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void printTree(ParseTree c,int depth){
        int l=c.getChildCount();
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<depth;++i) sb.append('\t');
        System.out.println(sb+c.getClass().getSimpleName()+": "+c.getText());
        for (int i=0;i<l;++i){
            printTree(c.getChild(i),depth+1);
        }
    }
}
