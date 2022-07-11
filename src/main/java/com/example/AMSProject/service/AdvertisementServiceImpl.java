package com.example.AMSProject.service;

import com.example.AMSProject.model.*;
import com.example.AMSProject.repository.ContentPageRepo;
import com.example.AMSProject.repository.ContentUserRepo;
import com.example.AMSProject.repository.ViewedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {


    @Autowired
    private ViewedRepo viewedRepo;

    @Autowired
    private ContentPageRepo contentPageRepo;

    @Autowired
    private ContentUserRepo contentUserRepo;


    /**
     * метод добавления просмотров пользователя
     * @param viewed структура Viewers с инф-ей о просмотрах
     * @return ответ от сервера в случае удачного добавляения
     */
    public ResponseEntity setViewed(Viewers viewed) {
        for (ViewedModel view : viewed.getViewed()) {
            viewedRepo.setViewed(view.getUserGuid(), view.getContentGuid());
        }
        return printAddSuccess();
    }


    /**
     * Получение таргетирования по наименованию страницы
     * @param namePage наименование страницы
     * @return структура PageTargetModel
     * @throws ParseException
     */
    public PageTargetModel getTarget(String namePage) throws ParseException {
        var model = contentUserRepo.getTargetForPage(namePage);
        List<TargetModel> targetPage = groupContent(model.stream());
        Date currentDate = new Date();
        PageTargetModel pageTargetModel = new PageTargetModel(namePage, dateForFormatJson(currentDate, 0), findNextDay(currentDate), targetPage);
        return pageTargetModel;
    }


    /**
     * Сохранение контента определенной страницы
     * @param contents  структура ContentModel с инф-ей о контенте
     * @return ответ от сервера в случае удачного добавляения
     */
    public ResponseEntity saveContentPage(ContentModel contents) {
        for (ContentList content : contents.getContent()) {
            String contentGuid = content.getContentGuid();
            List<PageModel> pageModels = content.getPages();
            for (PageModel page : pageModels) {
                this.setData(page.getPageName(), contentGuid);
            }
        }
        return printAddSuccess();
    }

    /**
     * метод работы с бд для сохранения наименования страницы
     * @param pageName наименование страницы
     * @param contentGuid guid контента
     */
    @Transactional
    private void setData(String pageName, String contentGuid) {
        contentPageRepo.setPage(pageName);
        contentPageRepo.setContentPage(contentGuid);
    }

    /**
     * шаблонный вывод
     * @return ответ 200 от сервера с сообщением
     */
    private ResponseEntity printAddSuccess() {
        return ResponseEntity.ok("Записи добавлены!");
    }

    /**
     * группировка контента по пользователям и установка приоритета
     * @param inputStream входной поток данных о пользователе и контенте
     * @return List<TargetModel>
     */
    private List<TargetModel> groupContent(Stream<ContentUserModel> inputStream) {

        Map<String, List<ContentUserModel>> contentByUsers = inputStream.collect(
                Collectors.groupingBy(ContentUserModel::getUserGuid));
        List<TargetModel> targets = new ArrayList<>();
        for (Map.Entry<String, List<ContentUserModel>> item : contentByUsers.entrySet()) {
            TargetModel target = new TargetModel();
            target.setUserGuid(item.getKey());
            List<OffersModel> offers = new ArrayList<>();
            for (ContentUserModel content : item.getValue()) {
                OffersModel offer = new OffersModel();
                offer.setContentGuid(content.getContentGuid());
                offer.setPriority(getRandomNumber((byte) 0, (byte) 100));
                offers.add(offer);
            }
            target.setOffers(offers);
            targets.add(target);
        }
        return targets;
    }

    /**
     * получение рандомного числа от 0 до 255
     * @param min - минимальное число
     * @param max - максимальное число
     * @return рандомное число в промежутке
     */
    private byte getRandomNumber(byte min, byte max) {
        return (byte) ((Math.random() * (max - min)) + min);
    }

    /**
     * получение следующего дня строкой
     * @param date дата от которой нужно найти следующий день
     * @return дата в формате json ddMMyyyy
     * @throws ParseException
     */
    private String findNextDay(Date date) throws ParseException {
        return dateForFormatJson(date, 1000 * 60 * 60 * 24);
    }

    /**
     * получение даты в json формате
     * @param date дата которой нужно установить json формат
     * @param ticks количество времени от начального для которого нужно получить json формат даты
     * @return строка с датой в формате json
     */
    private String dateForFormatJson(Date date, long ticks) {
        String pattern = "ddMMyyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date(date.getTime() + ticks));
    }


}
