package com.bigzhao.decoder;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by Roy on 15-9-19.
 */
public class PullReader implements Closeable {
    private InputStream f;

    public PullReader(String filename) throws FileNotFoundException {
        f = new FileInputStream(filename);
    }

    public PullReader(byte[] data) throws FileNotFoundException {
        f = new ByteArrayInputStream(data);
    }

    public byte[] pullUntil(int terminalByte) throws IOException {
        LinkedList<Byte> bs=new LinkedList<Byte>();
        while(true){
            int b=pull();
            bs.add((byte)b);
            if (b==terminalByte) break;
        }
        byte[] out=new byte[bs.size()];
        int i=0;
        for (Byte b:bs) out[i++]=b;
        System.out.println("===PULL:"+out.length);
        return out;
    }

    public byte[] pull(int len) throws IOException {
        byte[] bs = new byte[len];
        int l=f.read(bs);
        if (l==-1) throw new EndOfBlockException();
        if (l<len) return Arrays.copyOf(bs,l);
        return bs;
    }

    public int pull() throws IOException {
        int x=f.read();
        if (x==-1) throw new EndOfBlockException();
        return x;
    }

    @Override
    public void close() throws IOException {
        f.close();
    }
}
