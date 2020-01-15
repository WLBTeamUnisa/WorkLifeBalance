package it.unisa.wlb.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The aim of this class is implementing useful methods
 * 
 * @author Vincenzo Fabiano
 *
 */
public class Utils {
    public Utils() {
    }

    /**
     * Exposes the function to encrypt the password.
     * 
     * @param passwordToHash
     *            is the password to be encrypted.
     * @return the encrypted password.
     */
    public static String generatePwd(String passwordToHash) {
        String generatedPassword = null;
        String salt = "worklifebalance";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
