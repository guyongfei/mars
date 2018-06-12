package com.witshare.mars.pojo.util;

import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.InputStream;


public class InputStreamAndMetadata {
    private InputStream inputStream;
    private ObjectMetadata objectMetadata;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ObjectMetadata getObjectMetadata() {
        return objectMetadata;
    }

    public void setObjectMetadata(ObjectMetadata objectMetadata) {
        this.objectMetadata = objectMetadata;
    }
}
