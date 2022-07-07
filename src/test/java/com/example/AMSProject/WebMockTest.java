package com.example.AMSProject;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.AMSProject.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class WebMockTest {

    @Autowired
    private MockMvc mockMvc;


    /**
     * тест для контроллера AdvertisementController метода setViewed
     * в тест вшиты значения которые должны быть в тестовой бд
     * userGuid "ec77a962-fd38-11ec-b84f-b42e99986ec1" contentGuid "041ffa27-fd39-11ec-b84f-b42e99986ec1"
     * userGuid "ec0adca6-fd38-11ec-b84f-b42e99986ec1" contentGuid "03ae4812-fd39-11ec-b84f-b42e99986ec1"
     *
     * @throws Exception
     */
    @Test
    public void setViewedTest() throws Exception {
        List<ViewedModel> viewedModels = new ArrayList<>();
        viewedModels.add(new ViewedModel("ec77a962-fd38-11ec-b84f-b42e99986ec1", "041ffa27-fd39-11ec-b84f-b42e99986ec1"));
        viewedModels.add(new ViewedModel("ec0adca6-fd38-11ec-b84f-b42e99986ec1", "03ae4812-fd39-11ec-b84f-b42e99986ec1"));
        Viewers viewers = new Viewers(viewedModels);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String content = ow.writeValueAsString(viewers);

        setDataTest(content, "setViewed");
    }


    /**
     * тест для контроллера AdvertisementController метода getTargeting для страницы test1
     *
     * @throws Exception
     */
    @Test
    public void getTargetingTest() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/advertisement/getTargeting/test1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        String pattern = "ddMMyyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        var currentDate = new Date();
        var currentDay = simpleDateFormat.format(currentDate);
        var nextDay = simpleDateFormat.format(new Date(currentDate.getTime() + (1000 * 60 * 60 * 24)));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page").value("test1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value(currentDay))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value(nextDay))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value(nextDay))
                .andExpect(MockMvcResultMatchers.jsonPath("$.target").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.target[0].userGuid").value("ec77a962-fd38-11ec-b84f-b42e99986ec1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.target[0].offers").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.target[0].offers").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.target[0].offers[0].contentGuid").value("041ffa27-fd39-11ec-b84f-b42e99986ec1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.target[0].offers[0].priority").isNumber());

    }


    /**
     * тест для контроллера AdvertisementController метода setPublishedContent
     *
     * @throws Exception
     */
    @Test
    public void setPublishedContentTest() throws Exception {

        PageModel pageModel1 = new PageModel("test1");
        PageModel pageModel2 = new PageModel("test2");
        List<PageModel> pageModels = new ArrayList<>();
        pageModels.add(pageModel1);
        pageModels.add(pageModel2);
        ContentList contentList = new ContentList("041ffa27-fd39-11ec-b84f-b42e99986ec1", pageModels);
        List<ContentList> contentLists = new ArrayList<>();
        contentLists.add(contentList);
        ContentModel contentModel = new ContentModel(contentLists);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String content = ow.writeValueAsString(contentModel);

        setDataTest(content, "setPublishedContent");

    }


    private void setDataTest(String content, String nameMethod) throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/advertisement/" + nameMethod)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()));
    }


}