package com.lib.proto;

public class ChefTool {

    ChefTool() {

    }
    protected int serInt(int input, byte[] dst, int offset) {
        for (int i = 3; i >= 0; i--) {
            dst[i + offset] = (byte) (input & 0xFF);
            input >>= 8;
        }
        return offset + Integer.BYTES;
    }

    protected int serLong(long input, byte[] dst, int offset) {
        for (int i = 7; i >= 0; i--) {
            dst[i + offset] = (byte) (input & 0xFF);
            input >>= 8;
        }
        return offset + Long.BYTES;
    }

    protected int serChar(char x, byte[] dst, int offset) {
        dst[offset] = (byte) ((x >> 8) & 0xFF);
        dst[offset + 1] = (byte) (x & 0xFF);
        return offset + Character.BYTES;
    }

    protected int serFloat(float input, byte[] dst, int offset) {
        int x = Float.floatToRawIntBits(input);
        for (int i = 3; i >= 0; i--) {
            dst[i + offset] = (byte) (x & 0xFF);
            x >>= 8;
        }
        return offset + Float.BYTES;
    }

    protected int serDouble(double input, byte[] dst, int offset) {
        long x = Double.doubleToRawLongBits(input);
        for (int i = 7; i >= 0; i--) {
            dst[i + offset] = (byte) (x & 0xFF);
            x >>= 8;
        }
        return offset + Double.BYTES;
    }

    protected int serShort(short x, byte[] dst, int offset) {
        dst[offset] = (byte) ((x >> 8) & 0xff);
        dst[offset + 1] = (byte) (x & 0xff);
        return offset + Short.BYTES;
    }

}
