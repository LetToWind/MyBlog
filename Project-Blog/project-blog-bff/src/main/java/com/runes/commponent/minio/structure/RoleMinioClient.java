package com.runes.commponent.minio.structure;

import io.minio.MinioClient;
import lombok.Getter;

@Getter
public class RoleMinioClient extends MinioClient {
    private String role;

    public RoleMinioClient(String role,MinioClient minioClient){
        super(minioClient);
        this.role = role;
    }
}
