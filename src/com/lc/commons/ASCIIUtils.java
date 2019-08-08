package com.lc.commons;

public class ASCIIUtils {
    public static byte[] stringToAscii(String value) {
        char[] chars = value.toCharArray();
        byte[] bytes = new byte[chars.length];
        for (int i = 0; i < chars.length; i++) {
            bytes[i] = (byte) chars[i] ;
        }
        return bytes;
    }


    public static String asciiToString(String value) {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }

    public static String asciiToString(byte[] bts) {
        StringBuffer sbu = new StringBuffer();
        for (int i = 0; i < bts.length; i++) {
            sbu.append((char) bts[i]);
        }
        return sbu.toString();
    }
}
