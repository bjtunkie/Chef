package com.lib;

import com.lib.proto.ChefDeSerializer;
import com.lib.proto.ChefMap;
import com.lib.proto.ChefSerializer;

import java.nio.ByteBuffer;

public abstract class Chef {
    private final byte[] bytes = new byte[1024 * 1024 * 8]; // 8 mb of data


    private final ChefSerializer serializer;
    private final ChefDeSerializer deSerializer;

    Chef() {
        final ChefMap cache = new ChefMap();
        serializer = new ChefSerializer(cache);
        deSerializer = new ChefDeSerializer(cache);
    }

    <T> int serialize(T input, byte[] output, int offset) {
        int count = serializer.serialize(input, bytes, offset);
        System.arraycopy(bytes, 0, output, 0, count);
        return count;
    }

    <T> byte[] serialize(T input, int offset) {
        int count = serializer.serialize(input, bytes, offset);
        byte[] array = new byte[count];
        System.arraycopy(bytes, 0, array, 0, count);
        return array;
    }

    <T> T deSerialize(byte[] array, Class<T> clazz, int offset) {
        return deSerialize(array, clazz, offset, array.length - offset);
    }

    <T> T deSerialize(byte[] array, Class<T> clazz, int offset, int length) {
        ByteBuffer buffer = ByteBuffer.wrap(array, offset, length);
        return (T) deSerializer.deSerialize(ByteBuffer.wrap(array), clazz);
    }


}