package com.example.AMSProject.controller;


import com.example.AMSProject.model.ContentModel;
import com.example.AMSProject.model.PageTargetModel;
import com.example.AMSProject.model.Viewers;
import com.example.AMSProject.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


@RestController
@RequestMapping("/advertisement")
public class AdvertisementController {

   @Autowired
   private AdvertisementService amsService;


    @PostMapping("/setViewed")
    public ResponseEntity setViewed(@RequestBody Viewers viewed){
        try {
            return amsService.setViewed(viewed);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }


   @RequestMapping(value="/getTargeting/{page}", method=RequestMethod.GET)
   public PageTargetModel getTargeting(@PathVariable String page) throws ParseException {
           return amsService.getTarget(page);

   }


    @PostMapping("/setPublishedContent")
    public ResponseEntity setPublishedContent(@RequestBody ContentModel content){
        try {
            return amsService.saveContentPage(content);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }




}
