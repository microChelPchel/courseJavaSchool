package com.example.AMSProject;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.AMSProject.model.ViewedModel;
import com.example.AMSProject.model.Viewers;
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

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class WebMockTest {

    @Autowired
    private MockMvc mockMvc;


    //TODO: недописанный тест
    @Test
    public void setViewedTest() throws Exception{
        List<ViewedModel> viewedModels = new ArrayList<>();
        viewedModels.add(new ViewedModel("79d787f2-fafe-11ec-b84f-b42e99986ec1","1e01ff51-fbbc-11ec-b84f-b42e99986ec1"));
        viewedModels.add(new ViewedModel("a55085dc-fb13-11ec-b84f-b42e99986ec1","55d727e6-fafe-11ec-b84f-b42e99986ec1"));
        Viewers viewers = new Viewers(viewedModels);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String content = ow.writeValueAsString(viewers);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("http://localhost:8080/advertisement/setViewed")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());

    }

    //TODO: недописанный тест
    @Test
    public void getTargetingTest() throws Exception{

    }


    //TODO: недописанный тест
    @Test
    public void setPublishedContentTest() throws Exception{

    }

}