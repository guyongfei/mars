package com.witshare.mars.pojo.util;

import java.sql.Timestamp;


public class QingyunStorageBean {

    public static final String TYPE = "type";
    public static final String _DATA = "Data";
    public static final String OBJECT_NAME = "objectName";
    public static final String ID = "id";

    private Long id;
    private String content;
    private String token;
    private String contentType;
    private String objectName;
    private Timestamp createTime;

    public QingyunStorageBean() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
