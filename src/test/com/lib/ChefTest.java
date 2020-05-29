package com.lib;


import com.lib.test.SimpleObject;

public class ChefTest {

    public static void main(String... args) {
        SimpleObject simpleObject = new SimpleObject();
        simpleObject.set(12,65);


        Chef chef = new Chef();
        byte[] data = chef.serialize(simpleObject);


        SimpleObject newObject = chef.deSerialize(data, SimpleObject.class);
        System.out.println(newObject.getV1());
    }
}