package com.ppt.wsinventory.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * Created by User on 02/01/2018.
 */

public class HexStringConverter {
    private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();
    private static HexStringConverter hexStringConverter = null;

    private HexStringConverter()
    {}

    public static HexStringConverter getHexStringConverterInstance()
    {
        if (hexStringConverter==null) hexStringConverter = new HexStringConverter();
        return hexStringConverter;
    }

    public String stringToHex(String input) throws UnsupportedEncodingException
    {
        if (input == null) throw new NullPointerException();
        return asHex(input.getBytes());
    }

    public String hexToString(String txtInHex)
    {
        String hex = txtInHex;
        ByteBuffer buff = ByteBuffer.allocate(hex.length()/2);
        for (int i = 0; i < hex.length(); i+=2) {
            buff.put((byte)Integer.parseInt(hex.substring(i, i+2), 16));
        }
        buff.rewind();
        Charset cs = Charset.forName("UTF-8");                              // BBB
        CharBuffer cb = cs.decode(buff);
        return  cb.toString();
//        byte [] txtInByte = new byte [txtInHex.length() / 2];
//        int j = 0;
//        for (int i = 0; i < txtInHex.length(); i += 2)
//        {
//            txtInByte[j++] = Byte.parseByte(txtInHex.substring(i, i + 2), 16);
//        }
//        return new String(txtInByte);
    }

    private String asHex(byte[] buf)
    {
        char[] chars = new char[2 * buf.length];
        for (int i = 0; i < buf.length; ++i)
        {
            chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
            chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
        }
        return new String(chars);
    }
}
