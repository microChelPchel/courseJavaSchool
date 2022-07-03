package com.example.AMSProject.repository;

import com.example.AMSProject.entity.Viewed;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ViewedRepoImpl extends CrudRepository<Viewed, String> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO VIEWED (guid,user_guid,content_guid) VALUES (UUID(),:userGuid,:contentGuid)", nativeQuery = true)
    int setViewed(@Param("userGuid") String userGuid, @Param("contentGuid") String contentGuid);

}
