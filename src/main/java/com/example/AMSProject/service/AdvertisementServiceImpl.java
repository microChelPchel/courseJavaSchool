package com.example.AMSProject.service;

import com.example.AMSProject.model.ContentModel;
import com.example.AMSProject.model.PageTargetModel;
import com.example.AMSProject.model.Viewers;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;

public interface AdvertisementServiceImpl {

    ResponseEntity setViewed(Viewers viewed);

    PageTargetModel getTarget(String namePage) throws ParseException;

    ResponseEntity saveContentPage(ContentModel contents);
}
