package com.example.AMSProject.repository;

import com.example.AMSProject.model.ContentUserModel;

import java.util.List;

public interface ContentUserRepoImpl {

    List<ContentUserModel> getTargetForPage(String namePage);
}
