package com.example.AMSProject.model;

import java.io.Serializable;
import java.util.List;

public class TargetModel implements Serializable {

    private String userGuid;
    private List<OffersModel> offers;

    public TargetModel() {
    }

    public TargetModel(String userGuid, List<OffersModel> offers) {
        this.userGuid = userGuid;
        this.offers = offers;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public List<OffersModel> getOffers() {
        return offers;
    }

    public void setOffers(List<OffersModel> offers) {
        this.offers = offers;
    }
}
