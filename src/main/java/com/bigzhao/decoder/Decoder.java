package com.bigzhao.decoder;

import com.bigzhao.antlr.zfdLexer;
import com.bigzhao.antlr.zfdParser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.IOException;

/**
 * Created by Roy on 15-9-19.
 */
public class Decoder {
    private static int typeNameCnt=0;
    public static String newName(){
        return "!Anonymous"+(++typeNameCnt);
    }

    public static Code decode(String zfd,String target){
        try {
            CharStream cs = new ANTLRFileStream(zfd);
            zfdLexer l=new zfdLexer(cs);
            TokenStream ts = new CommonTokenStream(l);
            zfdParser p=new zfdParser(ts);
            Code root=new Code(null,target,p.code());
            root.addSysTypes();
            root.read(new PullReader(target),null,null);
            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
