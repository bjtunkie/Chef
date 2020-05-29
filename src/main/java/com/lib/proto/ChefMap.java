package com.lib.proto;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ChefMap {

    private final Map<Class, Field[]> listOfFields = new HashMap<>();

    private Field[] analyzeClass(Class<?> object) {
        Map<String, Field> map = new TreeMap<>();
        Class<?> clazz = object;
        while (!Object.class.equals(clazz)) {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                map.put(field.getName(), field);
            }
            clazz = clazz.getSuperclass();
        }

        return map.values().toArray(new Field[map.size()]);
    }

    public Field[] getMapping(Class<?> clazz) {

        Field[] f = listOfFields.get(clazz);
        if (f == null) {
            f = analyzeClass(clazz);
            listOfFields.put(clazz, f);
        }

        return f;

    }

}
