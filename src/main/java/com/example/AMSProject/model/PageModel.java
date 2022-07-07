package com.example.AMSProject.model;

import java.io.Serializable;

public class PageModel implements Serializable {

    private String PageName;

    public PageModel() {
    }

    public PageModel(String pageName) {
        PageName = pageName;
    }

    public String getPageName() {
        return PageName;
    }

    public void setPageName(String pageName) {
        PageName = pageName;
    }
}
