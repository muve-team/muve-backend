package kr.muve.common.security;

import kr.muve.common.exception.EncryptionException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypts {

    private static final MessageDigest SHA256;

    static {
        try {
            SHA256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new EncryptionException("SHA-256 algorithm not found", e);
        }
    }

    public static String sha256Encrypt(String plainText) {
        return bytesToHex(SHA256.digest(plainText.getBytes()));
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
