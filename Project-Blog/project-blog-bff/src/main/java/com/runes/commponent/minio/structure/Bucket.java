package com.runes.commponent.minio.structure;

public class Bucket {
    protected String bucketName;

    public Bucket(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return this.bucketName;
    }
}