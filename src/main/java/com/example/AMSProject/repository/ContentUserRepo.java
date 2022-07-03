package com.example.AMSProject.repository;

import com.example.AMSProject.model.ContentUserModel;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContentUserRepo implements ContentUserRepoImpl{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ContentUserModel> getTargetForPage(String name) {

        String queryStr = "select cont.guid contentGuid, view.user_guid userGuid from page as p\n" +
                "inner join content_page as cp on cp.page_guid=p.guid\n" +
                "inner join content as cont on cp.content_guid=cont.guid\n" +
                "inner join viewed as view on view.content_guid=cont.guid\n" +
                "where p.name= ?1 ;";
        try {
            Query query = entityManager.createNativeQuery(queryStr);
            query.setParameter(1,name);
            List<Object[]> objects = query.getResultList();
            List<ContentUserModel> list = new ArrayList<>(objects.size());
            for (Object[] object : objects) {
                list.add(new ContentUserModel((String)object[0],(String)object[1]));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }
}
