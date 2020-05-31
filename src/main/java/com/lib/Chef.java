package com.lib;

import com.lib.proto.ChefDeSerializer;
import com.lib.proto.ChefMap;
import com.lib.proto.ChefSerializer;

import java.nio.ByteBuffer;

public class Chef {


    private final ChefSerializer serializer;
    private final ChefDeSerializer deSerializer;

    public Chef() {
        final ChefMap cache = new ChefMap();
        serializer = new ChefSerializer(cache);
        deSerializer = new ChefDeSerializer(cache);
    }


    public <T> byte[] serialize(T input) {
        byte[] bytes = new byte[1024 * 1024 * 1];
        int count = serializer.serialize(input, bytes, 0);
        byte[] arr = new byte[count];
        System.arraycopy(bytes, 0, arr, 0, count);
        return arr;
    }

    public <T> T deSerialize(byte[] array, Class<T> clazz) {
        return (T) deSerializer.deSerialize(ByteBuffer.wrap(array), clazz);
    }


}