package com.hut.common.utils;

import java.security.MessageDigest;

/**
 * Created by Jared on 2017/2/13.
 */
public class DigestUtils {

    public static String StringToHexMd5(String s) throws Exception {

        byte[] digest = MessageDigest.getInstance("md5").digest(s.getBytes("utf8"));
        String md5String = toMd5String(digest);
        return md5String;
    }

    private static String toMd5String(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for (byte b:bytes
                ) {

            int temp = b&0xff;
            String s = Integer.toHexString(temp);
            s = s.length()>1?s : "0"+s;
            sb.append(s);
        }
        return sb.toString();
    }

}
