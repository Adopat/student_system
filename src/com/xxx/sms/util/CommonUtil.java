package com.xxx.sms.util;

/**
 * @author Adopat
 * @description: MD5加密算法
 * @date 2023/02/20 13:01
 **/

import java.security.MessageDigest;

/**
 * MD5加密算法
 */
public class CommonUtil {
    public static String MD5(String data)  {
        try {
            java.security.MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        } catch (Exception exception) {
        }
        return null;
    }
}
