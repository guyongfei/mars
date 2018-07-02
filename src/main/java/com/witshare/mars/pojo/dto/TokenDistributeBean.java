package com.witshare.mars.pojo.dto;

public class TokenDistributeBean {

    private String projectGid;
    private String password;
    private String keystore;
    private Integer[] userTxStatus;
    private Integer[] platformTxStatus;
    private Integer id;

    public String getProjectGid() {
        return projectGid;
    }

    public void setProjectGid(String projectGid) {
        this.projectGid = projectGid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKeystore() {
        return keystore;
    }

    public void setKeystore(String keystore) {
        this.keystore = keystore;
    }

    public Integer[] getUserTxStatus() {
        return userTxStatus;
    }

    public void setUserTxStatus(Integer[] userTxStatus) {
        this.userTxStatus = userTxStatus;
    }

    public Integer[] getPlatformTxStatus() {
        return platformTxStatus;
    }

    public void setPlatformTxStatus(Integer[] platformTxStatus) {
        this.platformTxStatus = platformTxStatus;
    }

    public Integer getId() {
        return id;
    }

    public TokenDistributeBean setId(Integer id) {
        this.id = id;
        return this;
    }
}
