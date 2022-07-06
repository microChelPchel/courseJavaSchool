package com.example.AMSProject.model;

import java.io.Serializable;
import java.util.List;

public class ContentList implements Serializable {

    private String ContentGuid;
    private List<PageModel> pages;

    public ContentList() {
    }

    public ContentList(String contentGuid, List<PageModel> pages) {
        ContentGuid = contentGuid;
        this.pages = pages;
    }

    public String getContentGuid() {
        return ContentGuid;
    }

    public void setContentGuid(String contentGuid) {
        ContentGuid = contentGuid;
    }

    public List<PageModel> getPages() {
        return pages;
    }

    public void setPages(List<PageModel> pages) {
        this.pages = pages;
    }
}
