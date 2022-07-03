package com.example.AMSProject.model;

public class ContentUserModel {

    private String contentGuid;
    private String userGuid;

    public ContentUserModel(Object[] singleResult) {
    }

    public ContentUserModel(String contentGuid, String userGuid) {
        this.contentGuid = contentGuid;
        this.userGuid = userGuid;
    }

    public String getContentGuid() {
        return contentGuid;
    }

    public void setContentGuid(String contentGuid) {
        this.contentGuid = contentGuid;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }
}
