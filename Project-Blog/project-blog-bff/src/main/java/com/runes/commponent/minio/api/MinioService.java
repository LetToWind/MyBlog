package com.runes.commponent.minio.api;

import com.runes.commponent.minio.context.MinioContext;
import com.runes.commponent.minio.context.MinioContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class MinioService {
    public String uploadFilePrivate(MultipartFile file){
        MinioContext minioContext = MinioContextHolder.getMinioContext();
        if(minioContext != null){
            return minioContext.uploadFilePrivate(file);
        }
        throw new RuntimeException("你没有权限操作文件");
    }

    public InputStream loadFilePrivate(String fileId){
        MinioContext minioContext = MinioContextHolder.getMinioContext();
        if (minioContext != null){
            return minioContext.loadFilePrivate(fileId);
        }
        throw new RuntimeException("你没有权限操作该文件");
    }
}
