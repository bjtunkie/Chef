package com.lib.proto;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;

public class ChefDeSerializer implements ChefTool {
    private final ChefMap cache;

    public ChefDeSerializer(ChefMap cache) {
        this.cache = cache;
    }


    public <T> T deSerialize(ByteBuffer buf, Class<T> clazz) {

        T instance = null;
        try {
            instance = clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        Field[] fields = cache.getMapping(clazz);
        for (Field field : fields) {
            int position = buf.position();
            Class<?> type = field.getType();
            Object valueAtField = deSerializeObject(buf, type);
            if (valueAtField == null && position == buf.position()) { // Means that the 'class Type' is neither of basic datatype nor a String object
                valueAtField = deSerialize(buf, type);
            }
            try {
                field.set(instance, valueAtField);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return instance;
    }

    private Object deSerializeObject(ByteBuffer buf, Class<?> baseclass) {

        if (baseclass.isArray()) {
            return deSerializeArray(buf, baseclass.getComponentType());
        } else {
            return deSerializeSingleObject(buf, baseclass);
        }
    }

    private Object deSerializeSingleObject(ByteBuffer buf, Class<?> baseClass) {

        if (int.class == baseClass || Integer.class == baseClass) {
            return buf.getInt();
        } else if (long.class == baseClass || Long.class == baseClass) {
            return buf.getLong();
        } else if (char.class == baseClass || Character.class == baseClass) {
            return buf.getChar();
        } else if (float.class == baseClass || Float.class == baseClass) {
            return buf.getFloat();
        } else if (double.class == baseClass || Double.class == baseClass) {
            return buf.getDouble();
        } else if (short.class == baseClass || Short.class == baseClass) {
            return buf.getShort();
        } else if (byte.class == baseClass || Byte.class == baseClass) {
            return buf.get();
        } else if (boolean.class == baseClass || Boolean.class == baseClass) {
            return buf.get() > 0;
        } else if (String.class == baseClass) {
            int numberOfBytes = buf.getInt();
            int pos = buf.position();
            String x = new String(buf.array(), pos, numberOfBytes);
            buf.position(pos + numberOfBytes);
            return x;
        } else {
            int x = isSerNull(buf.array(), buf.position());
            if (x > 0) {
                buf.position(x);
            }
            /**
             * Surely of unknown Type
             */
            return null;
        }
    }


    private Object deSerializeArray(ByteBuffer buf, Class<?> baseClass) {

        int lengthOfArray = buf.getInt();

        if (int.class == baseClass) {
            int[] x = new int[lengthOfArray];
            for (int i = 0; i < lengthOfArray; i++) {
                x[i] = buf.getInt();
            }

            return x;
        } else if (Integer.class == baseClass) {
            Integer[] x = new Integer[lengthOfArray];
            for (int i = 0; i < lengthOfArray; i++) {
                x[i] = buf.getInt();
            }
            return x;

        } else if (long.class == baseClass) {
            long[] x = new long[lengthOfArray];
            for (int i = 0; i < lengthOfArray; i++) {
                x[i] = buf.getLong();
            }
            return x;

        } else if (Long.class == baseClass) {
            Long[] x = new Long[lengthOfArray];
            for (int i = 0; i < lengthOfArray; i++) {
                x[i] = buf.getLong();
            }
            return x;

        } else if (char.class == baseClass) {
            char[] x = new char[lengthOfArray];
            for (int i = 0; i < lengthOfArray; i++) {
                x[i] = buf.getChar();
            }
            return x;

        } else if (Character.class == baseClass) {
            Character[] x = new Character[lengthOfArray];
            for (int i = 0; i < lengthOfArray; i++) {
                x[i] = buf.getChar();
            }
            return x;

        } else if (float.class == baseClass) {
            float[] x = new float[lengthOfArray];
            for (int i = 0; i < lengthOfArray; i++) {
                x[i] = buf.getFloat();
            }
            return x;

        } else if (Float.class == baseClass) {
            Float[] x = new Float[lengthOfArray];
            for (int i = 0; i < lengthOfArray; i++) {
                x[i] = buf.getFloat();
            }
            return x;

        } else if (double.class == baseClass) {
            double[] x = new double[lengthOfArray];
            for (int i = 0; i < lengthOfArray; i++) {
                x[i] = buf.getDouble();
            }
            return x;

        } else if (Double.class == baseClass) {
            Double[] x = new Double[lengthOfArray];
            for (int i = 0; i < lengthOfArray; i++) {
                x[i] = buf.getDouble();
            }
            return x;

        } else if (String.class == baseClass) {
            String[] x = new String[lengthOfArray];
            for (int i = 0; i < lengthOfArray; i++) {
                int numberOfBytes = buf.getInt();
                int pos = buf.position();
                x[i] = new String(buf.array(), pos, numberOfBytes);
                buf.position(pos + numberOfBytes);
            }
            return x;

        } else {
            int x = isSerNull(buf.array(), buf.position());
            if (x > 0) {
                buf.position(x);
            }
            /**
             * Surely of Unknown Type
             */
            return null;
        }

    }
}
