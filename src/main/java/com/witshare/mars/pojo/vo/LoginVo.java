package com.witshare.mars.pojo.vo;


public class LoginVo {
    private String email;
    private Boolean admin;
    private String managementPage;

    public LoginVo() {
    }

    public LoginVo(String email) {
        this.email = email;
    }

    public LoginVo(String email, Boolean isAdmin, String managementPage) {
        this.email = email;
        this.admin = isAdmin;
        this.managementPage = managementPage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getManagementPage() {
        return managementPage;
    }

    public void setManagementPage(String managementPage) {
        this.managementPage = managementPage;
    }
}
