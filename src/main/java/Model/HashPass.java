package Model;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class HashPass {

    public static String hash(String password) {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = null;
        byte[] hash = new byte[0];
        try {
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = skf.generateSecret(spec).getEncoded();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return toHex(salt) + ":" + toHex(hash);
    }

    public static boolean checkPassword(String originalPassword, String storedPassword) {
        String[] parts = storedPassword.split(":");
        int iterations = 1000;
        byte[] salt = fromHex(parts[0]);
        byte[] hash = fromHex(parts[1]);

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);

        SecretKeyFactory skf = null;
        byte[] testHash = new byte[0];

        try {
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            testHash = skf.generateSecret(spec).getEncoded();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int diff = hash.length ^ testHash.length;

        for (int i = 0; i < hash.length && i < testHash.length; i++) {
            diff |= hash[i] ^ testHash[i];
        }

        return diff == 0;
    }

    private static byte[] getSalt() {
        SecureRandom secureRandom = null;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    private static String toHex(byte[] bytes){

        BigInteger bi = new BigInteger(1, bytes);
        String hex = bi.toString(16);
        int paddingLength = (bytes.length * 2) - hex.length();

        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }

    private static byte[] fromHex(String hex)
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

}
