package com.bigzhao.decoder.type;

/**
 * Created by Roy on 15-9-19.
 */
public abstract class CustomType<T> implements IType<T>{

    protected T val;
    private String name;
    @Override
    public T getValue() {
        return val;
    }

    @Override
    public String getName() {
        if (name!=null) return name;
        return name=getClass().getSimpleName();
    }

    public void setName(String name) {
        this.name = name;
    }
}
