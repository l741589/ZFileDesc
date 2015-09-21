package com.bigzhao.decoder.type;

import com.bigzhao.decoder.PullReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Roy on 15-9-19.
 */
public interface IType<T> {
    String getName();
    T getValue();
    void read(PullReader reader, Object[] args, Map<String, String> config) throws IOException;
}
