package com.lib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lib.sampleobjects.ModerateObject;

import java.nio.ByteBuffer;

public class SerializeModerateObjectTest {
    public static void main(String... args) {


        ModerateObject o = new ModerateObject(15);
        o.setM(new ModerateObject(211));


        Chef chef = new Chef();
        byte[] bytes = chef.serialize(o);
        ModerateObject object = chef.deSerialize(bytes, ModerateObject.class);

        Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
        String data = gson.toJson(object);
        System.out.println(data);


        ByteBuffer buf = ByteBuffer.wrap(bytes);
        for (int i = 0; i < 15; i++) {
            System.out.print(buf.get() + " ");
        }

    }

}
