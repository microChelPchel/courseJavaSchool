package com.example.AMSProject.repository;

import com.example.AMSProject.model.ContentUserModel;

import java.util.List;

public interface ContentUserRepo {

    List<ContentUserModel> getTargetForPage(String namePage);
}
