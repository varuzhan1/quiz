package com.egstestmyquizi.demo.util;



import lombok.extern.slf4j.Slf4j;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;

@Slf4j
public class Encrypt {

    private static final  String accountSeperator = ":";

    private static final String key = "1234abcd";

    private static String decrypt(String message) throws Exception {
        byte[] bytesrc = convertHexString(message);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(StandardCharsets.UTF_8));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] retByte = cipher.doFinal(bytesrc);
        return new String(retByte);
    }

    private static String encrypt(String message) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(StandardCharsets.UTF_8));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        return toHexString(cipher.doFinal(message.getBytes(StandardCharsets.UTF_8)));
    }

    private static byte[] convertHexString(String ss) {
        byte[] digest = new byte[ss.length() / 2];
        for (int i = 0; i < digest.length; i++) {
            String byteString = ss.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte) byteValue;
        }
        return digest;
    }

    private static String toHexString(byte[] b) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            String plainText = Integer.toHexString(0xff & b[i]);
            if (plainText.length() < 2) {
                plainText = "0" + plainText;
            }
            hexString.append(plainText);
        }
        return hexString.toString();
    }

    public static String[] decodeAccount(String token) {
        try {
            String origi = Encrypt.decrypt(token);
            String[] parts = origi.split(Encrypt.accountSeperator);
            if (parts.length == 2 && !parts[0].equals("") && !parts[1].equals("")) {
                return parts;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public static String encodeAccount(String username, String password) {
        String encryptString = null;
        try {
            encryptString = Encrypt.encrypt(username + Encrypt.accountSeperator + password);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return encryptString;
    }
}
