package com.example.AMSProject.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PageTargetModel implements Serializable {

    private String page;
    private String startDate;
    private String endDate;
    private List<TargetModel> target;

    public PageTargetModel() {
    }

    public PageTargetModel(List<TargetModel> target) {
        this.target = target;
    }

    public PageTargetModel(String page, String startDate, String endDate, List<TargetModel> target) {
        this.page = page;
        this.startDate = startDate;
        this.endDate = endDate;
        this.target = target;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<TargetModel> getTarget() {
        return target;
    }

    public void setTarget(List<TargetModel> target) {
        this.target = target;
    }
}
