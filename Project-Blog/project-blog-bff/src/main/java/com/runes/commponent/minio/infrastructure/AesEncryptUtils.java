package com.runes.commponent.minio.infrastructure;

import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class AesEncryptUtils {

    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = new byte[]{'A', 'E', 'S', 'Y', 'F', 'Z', 'Z', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};
    public static final String DOLLAR = "$";

    public static String encrypt(String valueToEnc) {
        try {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encValue = c.doFinal(valueToEnc.getBytes());
            String encryptedValue = Base64.getEncoder().encodeToString(encValue);
            return encryptedValue;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(String encryptedValue) {

        try {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue = Base64.getDecoder().decode(encryptedValue);
            byte[] decValue = c.doFinal(decordedValue);
            String decryptedValue = new String(decValue);
            return decryptedValue;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Key generateKey() {
        Key key = new SecretKeySpec(keyValue, ALGORITHM);
        return key;
    }

    public static MediaType parseMediaTypeFromFileID(String fileId) {
        if (StringUtils.hasText(fileId)) {
            String sourceFileId = decrypt(fileId);
            if (StringUtils.hasText(sourceFileId.substring(sourceFileId.lastIndexOf(DOLLAR) + 1))) {
                return MediaType.parseMediaType(sourceFileId.substring(sourceFileId.lastIndexOf(DOLLAR) + 1));
            }
        }
        return MediaType.APPLICATION_OCTET_STREAM;
    }

}
