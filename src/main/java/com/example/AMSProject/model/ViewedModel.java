package com.example.AMSProject.model;

public class ViewedModel {

    private String userGuid;
    private String contentGuid;

    public ViewedModel() {
    }

    public ViewedModel(String userGuid, String contentGuid) {
        this.userGuid = userGuid;
        this.contentGuid = contentGuid;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public String getContentGuid() {
        return contentGuid;
    }

    public void setContentGuid(String contentGuid) {
        this.contentGuid = contentGuid;
    }
}
