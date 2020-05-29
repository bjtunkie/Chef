package com.lib.proto;

import com.lib.exception.TypeNotDefined;

import java.nio.CharBuffer;

public class ChefSpace extends ChefTool {

    protected <T> void serializeOnto(T input, byte[] dst, int offset) {
        Class<?> clazz = input.getClass();
        if (clazz.isArray()) {
            serializeArrayOnto(input, clazz.getComponentType(), dst, offset);
        } else {
            serializeOnto(input, clazz, dst, offset);
        }
    }

    protected <T> int serializeOnto(T input, Class<?> baseClass, byte[] dst, int offset) {

        int newOffset;
        int temp;
        if (int.class == baseClass || Integer.class == baseClass) {
            newOffset = serInt((int) input, dst, offset);
        } else if (long.class == baseClass || Long.class == baseClass) {
            newOffset = serLong((long) input, dst, offset);
        } else if (char.class == baseClass || Character.class == baseClass) {
            newOffset = serChar((char) input, dst, offset);
        } else if (float.class == baseClass || Float.class == baseClass) {
            newOffset = serFloat((float) input, dst, offset);
        } else if (double.class == baseClass || Double.class == baseClass) {
            newOffset = serDouble((Double) input, dst, offset);
        } else if (short.class == baseClass || Short.class == baseClass) {
            newOffset = serShort((Short) input, dst, offset);
        } else if (byte.class == baseClass || Byte.class == baseClass) {
            dst[offset] = (byte) input;
            newOffset = offset + 1;
        } else if (boolean.class == baseClass || Boolean.class == baseClass) {
            dst[offset] = (byte) (((boolean) input) ? 0xFF : 0);
            newOffset = offset + 1;
        } else if (String.class == baseClass) {
            String x = (String) input;
            x.getBytes(0, temp = x.length(), dst, offset);
            newOffset = offset + (temp * 2);
        } else {
            String e = "Error occurred while parsing value: " + input.toString();
            System.out.println(e);
            System.out.print(baseClass.toString());
            throw new TypeNotDefined();
        }
        return newOffset;
    }

    protected <T> int serializeArrayOnto(T t, Class<?> baseClass, byte[] dst, int offset) {

        if (int.class == baseClass) {
            int[] input = (int[]) t;
            for (int i = 0; i < input.length; i++) {
                offset = serInt(input[i], dst, offset);
            }
        } else if (Integer.class == baseClass) {
            Integer[] input = (Integer[]) t;
            for (int i = 0; i < input.length; i++) {
                offset = serInt(input[i], dst, offset);
            }
        } else if (long.class == baseClass) {
            long[] input = (long[]) t;
            for (int i = 0; i < input.length; i++) {
                offset = serLong(input[i], dst, offset);
            }
        } else if (Long.class == baseClass) {
            Long[] input = (Long[]) t;
            for (int i = 0; i < input.length; i++) {
                offset = serLong(input[i], dst, offset);
            }
        } else if (char.class == baseClass) {
            char[] input = (char[]) t;
            for (int i = 0; i < input.length; i++) {
                offset = serChar(input[i], dst, offset);
            }
        } else if (Character.class == baseClass) {
            Character[] input = (Character[]) t;
            for (int i = 0; i < input.length; i++) {
                offset = serChar(input[i], dst, offset);
            }
        } else if (float.class == baseClass) {
            float[] input = (float[]) t;
            for (int i = 0; i < input.length; i++) {
                offset = serFloat(input[i], dst, offset);
            }
        } else if (Float.class == baseClass) {
            Float[] input = (Float[]) t;
            for (int i = 0; i < input.length; i++) {
                offset = serFloat(input[i], dst, offset);
            }
        } else if (double.class == baseClass) {
            double[] input = (double[]) t;
            for (int i = 0; i < input.length; i++) {
                offset = serDouble(input[i], dst, offset);
            }
        } else if (Double.class == baseClass) {
            Double[] input = (Double[]) t;
            for (int i = 0; i < input.length; i++) {
                offset = serDouble(input[i], dst, offset);
            }
        } else {
            throw new TypeNotDefined();
        }

        return offset;
    }

}
