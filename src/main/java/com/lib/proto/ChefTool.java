package com.lib.proto;

public interface ChefTool {
    int MAGIC_NUMBER_FOR_NULL = 3;

    default int serNull(byte[] dst, int offset) {
        offset = serInt(MAGIC_NUMBER_FOR_NULL, dst, offset);
        for (int i = 0; i < MAGIC_NUMBER_FOR_NULL; i++) {
            dst[i + offset] = Byte.MIN_VALUE;
        }
        return offset + MAGIC_NUMBER_FOR_NULL;

    }

    default int isSerNull(byte[] src, int offset) {
        if (src[offset + 3] == MAGIC_NUMBER_FOR_NULL) {
            boolean x = true;
            for (int i = (offset + 4), len = (i + MAGIC_NUMBER_FOR_NULL); i < len; i++) {
                if (src[i] != Byte.MIN_VALUE) x = false;
            }
            return x ? (offset + 4 + MAGIC_NUMBER_FOR_NULL) : -1;
        }
        return -1;
    }

    default int serInt(int input, byte[] dst, int offset) {
        for (int i = 3; i >= 0; i--) {
            dst[i + offset] = (byte) (input & 0xFF);
            input >>= 8;
        }
        return offset + Integer.BYTES;
    }

    default int serLong(long input, byte[] dst, int offset) {
        for (int i = 7; i >= 0; i--) {
            dst[i + offset] = (byte) (input & 0xFF);
            input >>= 8;
        }
        return offset + Long.BYTES;
    }

    default int serChar(char x, byte[] dst, int offset) {
        dst[offset] = (byte) ((x >> 8) & 0xFF);
        dst[offset + 1] = (byte) (x & 0xFF);
        return offset + Character.BYTES;
    }

    default int serFloat(float input, byte[] dst, int offset) {
        int x = Float.floatToRawIntBits(input);
        for (int i = 3; i >= 0; i--) {
            dst[i + offset] = (byte) (x & 0xFF);
            x >>= 8;
        }
        return offset + Float.BYTES;
    }

    default int serDouble(double input, byte[] dst, int offset) {
        long x = Double.doubleToRawLongBits(input);
        for (int i = 7; i >= 0; i--) {
            dst[i + offset] = (byte) (x & 0xFF);
            x >>= 8;
        }
        return offset + Double.BYTES;
    }

    default int serShort(short x, byte[] dst, int offset) {
        dst[offset] = (byte) ((x >> 8) & 0xff);
        dst[offset + 1] = (byte) (x & 0xff);
        return offset + Short.BYTES;
    }

}
