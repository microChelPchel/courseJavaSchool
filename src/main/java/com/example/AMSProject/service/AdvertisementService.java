package com.example.AMSProject.service;

import com.example.AMSProject.model.*;
import com.example.AMSProject.repository.ContentPageRepoImpl;
import com.example.AMSProject.repository.ContentUserRepoImpl;
import com.example.AMSProject.repository.ViewedRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AdvertisementService implements AdvertisementServiceImpl {


    @Autowired
    private ViewedRepoImpl viewedRepo;

    @Autowired
    private ContentPageRepoImpl contentPageRepo;

    @Autowired
    private ContentUserRepoImpl contentUserRepo;


    public ResponseEntity setViewed(Viewers viewed){
        for(ViewedModel view : viewed.getViewed()) {
            viewedRepo.setViewed(view.getUserGuid(), view.getContentGuid());
        }
        return printAddSuccess();
    }


    public PageTargetModel getTarget(String namePage) throws ParseException {
       var model = contentUserRepo.getTargetForPage(namePage);
       List<TargetModel> targetPage = groupContent(model.stream());
        Date currentDate = new Date();
        PageTargetModel pageTargetModel = new PageTargetModel(namePage, dateForFormatJson(currentDate,0), findNextDay(currentDate),targetPage);
        return pageTargetModel;
    }



    public ResponseEntity saveContentPage(ContentModel contents){
        for(ContentList content : contents.getContent()){
            String contentGuid = content.getContentGuid();
            List<PageModel> pageModels = content.getPages();
            contentPageRepo.setContent(contentGuid);
            for(PageModel page : pageModels){
                contentPageRepo.setPage(page.getPageName());
                contentPageRepo.setContentPage(contentGuid);
            }
        }
        return printAddSuccess();
    }

    private ResponseEntity printAddSuccess(){
        return  ResponseEntity.ok("Записи добавлены!");
    }

    private List<TargetModel> groupContent(Stream<ContentUserModel> inputStream) {

        Map<String, List<ContentUserModel>> contentByUsers = inputStream.collect(
                Collectors.groupingBy(ContentUserModel::getUserGuid));
        List<TargetModel> targets = new ArrayList<>();
        for (Map.Entry<String, List<ContentUserModel>> item : contentByUsers.entrySet()) {
            TargetModel target = new TargetModel();
            target.setUserGuid(item.getKey());
            List<OffersModel> offers = new ArrayList<>();
            for (ContentUserModel content : item.getValue()) {
                OffersModel offer =new OffersModel();
                offer.setContentGuid(content.getContentGuid());
                offer.setPriority(getRandomNumber((byte)0,(byte) 100));
                offers.add(offer);
            }
            target.setOffers(offers);
            targets.add(target);
        }
        return targets;
    }

    private byte getRandomNumber(byte min, byte max) {
        return (byte) ((Math.random() * (max - min)) + min);
    }


    private String findNextDay(Date date) throws ParseException {
        return dateForFormatJson(date,1000 * 60 * 60 * 24);
    }

    private String dateForFormatJson(Date date,long ticks){
        String pattern = "ddMMyyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date(date.getTime() +ticks));
    }


}
