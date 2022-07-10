package com.example.AMSProject.controller;

import com.example.AMSProject.model.*;
import com.example.AMSProject.service.AdvertisementService;
import com.example.AMSProject.service.AdvertisementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


@RestController
@RequestMapping("/advertisement")
public class AdvertisementController {

   @Autowired
   private AdvertisementServiceImpl amsService;

    /**
     * POST /advertisement/setViewed
     * добавление не просмотренной рекламы пользователем
     * используемая структура: class ViewedModel
     * поля ViewedModel: String userGuid, String contentGuid
     * @param viewed - ViewedModel[]
     * @return код состояния
     */
    @PostMapping("/setViewed")
    public ResponseEntity setViewed(@RequestBody Viewers viewed){
        try {
            return amsService.setViewed(viewed);
       }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
       }
    }

    /**
     * POST /advertisement/getTargeting
     * параметром является page строкового значения с наименованием страницы
     * Получение информации по странице за сегодняшний день
     * Структура PageTargetModel
     * Поля PageTargetModel:
     * String page - наименование страницы
     * String startDate - начало периода
     * String endDate - окончание периода
     * List<TargetModel> target - список пользователей и контента
     * Стркутура TargetModel
     * Поля TargetModel
     * String userGuid - идентификатор пользователя
     * List<OffersModel> offers
     * Структура OffersModel
     * Поля OffersModel
     * String contentGuid - идентификатор контента
     * byte priority - приоритет контента, выбирается случайно
     * @param page - наименование страницы
     * @return PageTargetModel
     * @throws ParseException
     */
   @PostMapping("/getTargeting")
   public PageTargetModel getTargeting(@RequestBody String page) throws ParseException {
           return amsService.getTarget(page);
   }

    /**
     * POST /advertisement/setPublishedContent
     * добавление информации о контенте предназначенный для каждой страницы
     * используемая структура: class ContentModel
     * поля ContentModel: String сontentGuid, List<PageModel> pages
     * структура: PageModel
     * Поля PageModel: String PageName
     * @param content - ContentModel[]
     * @return код состояния
     */
    @PostMapping("/setPublishedContent")
    public ResponseEntity setPublishedContent(@RequestBody ContentModel content){
        try {
            return amsService.saveContentPage(content);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
