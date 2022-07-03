package com.example.AMSProject.model;

import java.io.Serializable;

public class OffersModel implements Serializable {

    private String contentGuid;
    private byte priority;

    public OffersModel() {
    }

    public OffersModel(String contentGuid, byte priority) {
        this.contentGuid = contentGuid;
        this.priority = priority;
    }

    public String getContentGuid() {
        return contentGuid;
    }

    public void setContentGuid(String contentGuid) {
        this.contentGuid = contentGuid;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }
}
