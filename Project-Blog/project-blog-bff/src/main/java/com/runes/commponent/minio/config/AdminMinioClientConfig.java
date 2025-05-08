package com.runes.commponent.minio.config;

import com.runes.commponent.minio.structure.RoleMinioClient;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import static com.runes.commponent.minio.infrastructure.MinioConstants.ROLE_ADMIN;

@Configuration
@Slf4j
@ConditionalOnClass(MinioClient.class)
public class AdminMinioClientConfig implements RoleMinioClientConfig {

    @Value(value = "${minio.endpoint}")
    private String endpoint;
    @Value(value = "${minio.port}")
    private int port;
    @Value(value = "${minio.secure}")
    private boolean secure;
    @Value(value = "${minio.admin-user.access-key}")
    private String adminAccessKey;
    @Value(value = "${minio.admin-user.secret-key}")
    private String adminSecretKey;
    private MinioClient adminMiniClient;

    @Override
    public void afterPropertiesSet() {
        Assert.hasText(endpoint, "Minio endpoint 为空");
        Assert.notNull(port, "Minio port为空");
        Assert.notNull(secure, "Minio secure为空");
        Assert.hasText(adminAccessKey, "Minio admin accessKey为空");
        Assert.hasText(adminSecretKey, "Minio admin secretKey为空");
        log.info("endpoint: {} | port: {} |secure: {} \n Try to create admin minioClient...", endpoint, port, secure);
        this.adminMiniClient = MinioClient.builder()
                .endpoint(endpoint, port, secure)
                .credentials(adminAccessKey, adminSecretKey)
                .build();
    }
    @Bean(name = "adminMinioClient")
    public RoleMinioClient adminMinioClient() {
        return new RoleMinioClient(ROLE_ADMIN,this.adminMiniClient);
    }
}
