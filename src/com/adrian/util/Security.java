package com.adrian.util;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

public class Security {

    private static Cipher cipher;
    private static SecretKey secretKey;
    private static final String key = "Sixteen byte key";


    public static void init() {
        secretKey = new SecretKeySpec(key.getBytes(), "AES");
        try {
            cipher = Cipher.getInstance("AES/CFB8/NoPadding");
            System.out.println(cipher.getBlockSize());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String message){
        String result = "";
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            result = new String(cipher.doFinal(message.getBytes()));
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String decrypt(String message){
        String result = "";
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            result = new String(cipher.doFinal(message.getBytes()));
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return result;
    }

}