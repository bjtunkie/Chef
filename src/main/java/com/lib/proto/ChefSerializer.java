package com.lib.proto;

import java.lang.reflect.Field;

public class ChefSerializer implements ChefTool {


    private final ChefMap cache;

    public ChefSerializer(ChefMap cache) {
        this.cache = cache;
    }

    public <T> int serialize(T input, byte[] dst, int offset) {

        int mark;
        Field[] fields = cache.getMapping(input.getClass());
        try {
            for (Field field : fields) {
                Class<?> k = field.getType();
                Object valueAtField = field.get(input);
                if (valueAtField == null) {
                    offset = serNull(dst, offset);
                    continue;
                }

                mark = k.isArray() ?
                        serializeObjectArray(valueAtField, k.getComponentType(), dst, offset) :
                        serializeSingleObject(valueAtField, k, dst, offset);
                if (mark < 0) {
                    offset = serialize(valueAtField, dst, offset);
                } else {
                    offset = mark;
                }


            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return offset;
    }





    private <T> int serializeSingleObject(T input, Class<?> baseClass, byte[] dst, int offset) {

        if (int.class == baseClass || Integer.class == baseClass) {
            return serInt((int) input, dst, offset);
        } else if (long.class == baseClass || Long.class == baseClass) {
            return serLong((long) input, dst, offset);
        } else if (char.class == baseClass || Character.class == baseClass) {
            return serChar((char) input, dst, offset);
        } else if (float.class == baseClass || Float.class == baseClass) {
            return serFloat((float) input, dst, offset);
        } else if (double.class == baseClass || Double.class == baseClass) {
            return serDouble((Double) input, dst, offset);
        } else if (short.class == baseClass || Short.class == baseClass) {
            return serShort((Short) input, dst, offset);
        } else if (byte.class == baseClass || Byte.class == baseClass) {
            dst[offset] = (byte) input;
            return offset + 1;
        } else if (boolean.class == baseClass || Boolean.class == baseClass) {
            dst[offset] = (byte) (((boolean) input) ? 0xFF : 0);
            return offset + 1;
        } else if (String.class == baseClass) {
            byte[] bytes = ((String) input).getBytes();
            offset = serInt(bytes.length, dst, offset);
            System.arraycopy(bytes, 0, dst, offset, bytes.length);
            return offset + bytes.length;
        } else {


            /**
             * Surely of unknown Typee
             */
            return -1;
        }
    }

    private <T> int serializeObjectArray(T t, Class<?> baseClass, byte[] dst, int offset) {

        if (int.class == baseClass) {
            int[] input = (int[]) t;
            offset = serInt(input.length, dst, offset);
            for (int i = 0; i < input.length; i++) {
                offset = serInt(input[i], dst, offset);
            }

        } else if (Integer.class == baseClass) {
            Integer[] input = (Integer[]) t;
            offset = serInt(input.length, dst, offset);

            for (int i = 0; i < input.length; i++) {
                offset = serInt(input[i], dst, offset);
            }
        } else if (long.class == baseClass) {
            long[] input = (long[]) t;
            offset = serInt(input.length, dst, offset);

            for (int i = 0; i < input.length; i++) {
                offset = serLong(input[i], dst, offset);
            }
        } else if (Long.class == baseClass) {
            Long[] input = (Long[]) t;
            offset = serInt(input.length, dst, offset);

            for (int i = 0; i < input.length; i++) {
                offset = serLong(input[i], dst, offset);
            }
        } else if (char.class == baseClass) {
            char[] input = (char[]) t;
            offset = serInt(input.length, dst, offset);

            for (int i = 0; i < input.length; i++) {
                offset = serChar(input[i], dst, offset);
            }
        } else if (Character.class == baseClass) {
            Character[] input = (Character[]) t;
            offset = serInt(input.length, dst, offset);

            for (int i = 0; i < input.length; i++) {
                offset = serChar(input[i], dst, offset);
            }
        } else if (float.class == baseClass) {
            float[] input = (float[]) t;
            offset = serInt(input.length, dst, offset);

            for (int i = 0; i < input.length; i++) {
                offset = serFloat(input[i], dst, offset);
            }
        } else if (Float.class == baseClass) {
            Float[] input = (Float[]) t;
            offset = serInt(input.length, dst, offset);

            for (int i = 0; i < input.length; i++) {
                offset = serFloat(input[i], dst, offset);
            }
        } else if (double.class == baseClass) {
            double[] input = (double[]) t;
            offset = serInt(input.length, dst, offset);

            for (int i = 0; i < input.length; i++) {
                offset = serDouble(input[i], dst, offset);
            }
        } else if (Double.class == baseClass) {
            Double[] input = (Double[]) t;
            offset = serInt(input.length, dst, offset);

            for (int i = 0; i < input.length; i++) {
                offset = serDouble(input[i], dst, offset);
            }
        } else if (byte.class == baseClass) {
            byte[] input = (byte[]) t;
            offset = serInt(input.length, dst, offset);
            System.arraycopy(input, 0, dst, offset, input.length);
            offset += input.length;
        } else if (Byte.class == baseClass) {
            byte[] input = (byte[]) t;
            offset = serInt(input.length, dst, offset);
            System.arraycopy(input, 0, dst, offset, input.length);
            offset += input.length;
        } else if (boolean.class == baseClass) {
            throw new RuntimeException("Yet to Implement");
        } else if (Boolean.class == baseClass) {
            throw new RuntimeException("Yet to Implement");
        } else if (String.class == baseClass) {
            String[] input = (String[]) t;
            offset = serInt(input.length, dst, offset);
            for (int i = 0; i < input.length; i++) {
                byte[] x = input[i].getBytes();
                offset = serInt(x.length, dst, offset);
                System.arraycopy(x, 0, dst, offset, x.length);
                offset += x.length;
            }

        } else {
            /**
             * Surely of Unknown Type
             */
            return -1;
        }

        return offset;
    }
}
