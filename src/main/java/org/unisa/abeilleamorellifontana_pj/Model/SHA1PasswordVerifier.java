package org.unisa.abeilleamorellifontana_pj.Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1PasswordVerifier {
    public static String sha1Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verifyPassword(String inputPassword, String storedHash) {
        String hashedInputPassword = sha1Hash(inputPassword);
        return hashedInputPassword.equals(storedHash);
    }
}
