package com.adrian.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;

public class Security {

    private static KeyPairGenerator kpg;
    private static KeyPair keyPair;
    private static Cipher cipher;

    public static void init(){
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
            cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        kpg.initialize(512);
        keyPair = kpg.generateKeyPair();
    }

    public static PublicKey getPublicKey(){
        return keyPair.getPublic();
    }

    public static PrivateKey getPrivateKey(){
        return keyPair.getPrivate();
    }

    public static String encrypt(String text, Key key){
        String result = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            result = new String(cipher.doFinal(text.getBytes()));
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String decrypt(String text, Key key){
        String result = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            result = new String(cipher.doFinal(text.getBytes()));
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return result;
    }

}
