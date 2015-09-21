package com.bigzhao.decoder.type;

import com.bigzhao.decoder.Expression;
import com.bigzhao.decoder.PullReader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by Roy on 15-9-19.
 */
public class Types {

    public static byte[] adapterEndian(byte[] bs,Map<String,String> config) throws IOException {
        String endian = config.get("endian");
        if (endian != null && endian.toLowerCase().contains("big")) return bs;
        int l = bs.length;
        byte[] out = new byte[l];
        for (int i = 0; i < l; ++i) {
            out[i] = bs[l - 1 - i];
        }
        return out;
    }



    public static class int32 extends CustomType<Integer>{
        @Override
        public void read(PullReader reader, Object[] args, Map<String, String> config) throws IOException {
            byte[] bs=reader.pull(4);
            bs=adapterEndian(bs,config);
            val =(int)(bs[0]&0xff);
            val <<=8;
            val |=(int)(bs[1]&0xff);
            val <<=8;
            val |=(int)(bs[2]&0xff);
            val <<=8;
            val |=(int)(bs[3]&0xff);
        }
    }

    public static class int16 extends CustomType<Integer>{

        @Override
        public void read(PullReader reader, Object[] args, Map<String, String> config) throws IOException {
            byte[] bs=reader.pull(2);
            bs=adapterEndian(bs,config);
            val =(int)(bs[0]&0xff);
            val <<=8;
            val |=(int)(bs[1]&0xff);
        }
    }

    public static class int8 extends CustomType<Integer>{
        @Override
        public void read(PullReader reader, Object[] args, Map<String, String> config) throws IOException {
            val =reader.pull();
        }
    }

    public static class leb extends CustomType<Integer>{
        @Override
        public void read(PullReader reader, Object[] args, Map<String, String> config) throws IOException {
            val=0;
            while(true) {
                int y = reader.pull();
                val |=y&0x7f;
                if (y<128) break;
                val <<=7;
            }
        }
    }

    public static class string extends CustomType<String>{

        @Override
        public void read(PullReader reader, Object[] args, Map<String, String> config) throws IOException {
            byte[] bs=args!=null&&args.length>0?reader.pull(Expression.ResolveInt(args[0])):reader.pullUntil(0);
            String encoding=null;
            if (args!=null&&args.length>1) encoding=args[1].toString();
            if (encoding==null) encoding=config.get("encoding");
            if (encoding==null) val =new String(bs);
            else val =new String(bs, Charset.forName(encoding));
        }
    }
}
