package com.example.AMSProject.repository;

import com.example.AMSProject.entity.Viewed;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;


public interface ContentPageRepoImpl extends CrudRepository<Viewed, String> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO PAGE (guid,name) VALUES (UUID(),:pageName);",
            nativeQuery = true)
    int setPage(@Param("pageName") String pageName);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO CONTENT_PAGE(CONTENT_GUID,PAGE_GUID) VALUES (:contentGuid,(SELECT P.GUID FROM PAGE AS P ORDER BY P.GUID DESC LIMIT 1));",
            nativeQuery = true)
    int setContentPage(@Param("contentGuid") String contentGuid);
}