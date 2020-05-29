package com.lib;


import com.lib.test.SimpleObject;

import java.nio.ByteBuffer;

public class SerializeOntoTest {

    public static void main(String... args) {
        System.out.println("Start-");
        test1();
        System.out.println("\nFinished.");
    }

    public static void test1() {
        SimpleObject simpleObject = new SimpleObject();
        simpleObject.set(12, 95);
        Chef chef = new Chef();
        byte[] data = chef.serialize(simpleObject);

        ByteBuffer buf = ByteBuffer.wrap(data);
        System.out.println(buf.getInt());
        System.out.println(buf.getInt());
    }
}