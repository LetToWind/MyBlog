package com.runes.adapt;

import com.runes.commponent.minio.api.MinioService;
import com.runes.commponent.minio.infrastructure.AesEncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/file")
public class TestMinioSecurityController {
    @Autowired
    private MinioService minioService;

    @GetMapping(value = "/download")
    public ResponseEntity<Resource> download(@RequestBody FileInput input) {
        String fileId = input.getFileId();
        return ResponseEntity
                .ok()
                .contentType(AesEncryptUtils.parseMediaTypeFromFileID(fileId))
                .body(new InputStreamResource(minioService.loadFilePrivate(fileId)));
    }

    @GetMapping(value = "/download2")
    public ResponseEntity<Resource> download2(@RequestParam("fileId") String fileId) {
        return ResponseEntity
                .ok()
                .contentType(AesEncryptUtils.parseMediaTypeFromFileID(fileId))
                .body(new InputStreamResource(minioService.loadFilePrivate(fileId)));
    }


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> filesUploadPic(@RequestPart(value = "file") MultipartFile file) {
        return ResponseEntity
                .ok()
                .body(minioService.uploadFilePrivate(file));
    }
}
