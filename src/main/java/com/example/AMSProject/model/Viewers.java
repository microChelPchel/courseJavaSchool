package com.example.AMSProject.model;


import java.util.List;

public class Viewers {

    private List<ViewedModel> viewed;

    public List<ViewedModel> getViewed() {
        return viewed;
    }

    public Viewers() {
    }

    public Viewers(List<ViewedModel> viewed) {
        this.viewed = viewed;
    }

    public void setViewed(List<ViewedModel> viewed) {
        this.viewed = viewed;
    }
}
