package com.example.AMSProject.model;

import java.io.Serializable;
import java.util.List;

public class ContentModel implements Serializable {

    private List<ContentList> content;

    public ContentModel() {
    }

    public ContentModel(List<ContentList> content) {
        this.content = content;
    }

    public List<ContentList> getContent() {
        return content;
    }

    public void setContent(List<ContentList> content) {
        this.content = content;
    }
}
