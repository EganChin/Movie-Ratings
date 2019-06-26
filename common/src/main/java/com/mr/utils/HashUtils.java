package com.mr.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author LiangYongjie
 * @date 2019-01-15
 */
public class HashUtils {

    private static final char[] HEX = "0123456789ABCDEF".toCharArray();

    public static String sha1Digest(String message) {
        return digestAsHex(message, "sha1");
    }

    public static String md5Digest(String message) {
        return digestAsHex(message, "md5");
    }

    public static String sha256Digest(String message) {
        return digestAsHex(message, "sha-256");
    }

    private static String digestAsHex(String message, String type) {
        try {
            MessageDigest digest = MessageDigest.getInstance(type);
            digest.update(message.getBytes());
            byte[] bytes = digest.digest();

            return bytesToHex(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 字节数组转换为十六进制字符串
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        if (bytes == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(HEX[(b >> 4) & 0xF]);
            sb.append(HEX[b & 0xF]);
        }

        return sb.toString();
    }

}
