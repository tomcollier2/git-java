package org.skunk.util;

import java.security.MessageDigest;

public class SHA1 {

    /*
    Hash functionality computes the SHA-1 hash of a byte array and
    returns a hex string.
    */
    public static String hash(byte[] data) {

        try {
            
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] hash = digest.digest(data);
            
            StringBuilder builder = new StringBuilder();

            for (byte b : hash) {
                builder.append(String.format("%02x", b));
            }

            return builder.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
