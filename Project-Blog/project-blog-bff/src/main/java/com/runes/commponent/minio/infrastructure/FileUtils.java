package com.runes.commponent.minio.infrastructure;

import lombok.experimental.UtilityClass;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import static com.runes.commponent.minio.infrastructure.MinioConstants.DOLLAR;

@UtilityClass
public class FileUtils {
    public static void checkFile() {
    }

    public static String generateObjectId(String name) {
        return UUID.randomUUID()+name;
    }

    public static String generateFileId(String objectId, String contentType) {
        String sourceFileId = objectId + DOLLAR + contentType;
        return AesEncryptUtils.encrypt(sourceFileId);
    }

    public static String parseObjectId(String fileId) {
        String sourceFileId = AesEncryptUtils.decrypt(fileId);
        return sourceFileId.substring(0, sourceFileId.lastIndexOf(DOLLAR));
    }
}
