package com.lib;

public class DefaultChef extends Chef {
    public DefaultChef(){
        super();
    }

    @Override
    public <T> byte[] serialize(T input, int offset) {
        return super.serialize(input, offset);
    }


    @Override
    public <T> T deSerialize(byte[] array, Class<T> clazz, int offset) {
        return super.deSerialize(array, clazz, offset);
    }
}
