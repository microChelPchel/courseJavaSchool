package com.example.AMSProject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "content")
public class Content implements Serializable {

    @Id
    private String guid;

    @OneToMany(mappedBy="content")
    private List<Viewed> viewed;


    @ManyToMany
    @JoinTable(
            name = "content_page",
            joinColumns = @JoinColumn(name = "content_guid"),
            inverseJoinColumns = @JoinColumn(name = "page_guid"))
    private List<Page> contentPage;

    public Content(){}

    public Content(String guid, List<Viewed> viewed, List<Page> contentPage) {
        this.guid = guid;
        this.viewed = viewed;
        this.contentPage = contentPage;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public List<Viewed> getViewed() {
        return viewed;
    }

    public void setViewed(List<Viewed> viewed) {
        this.viewed = viewed;
    }

    public List<Page> getContentPage() {
        return contentPage;
    }

    public void setContentPage(List<Page> contentPage) {
        this.contentPage = contentPage;
    }
}
