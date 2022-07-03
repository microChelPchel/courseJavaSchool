package com.example.AMSProject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="page")
public class Page implements Serializable {

    @Id
    private String guid;

    private String name;

    @ManyToMany(mappedBy = "contentPage")
    private List<Content> contents;

    public Page() {
    }

    public Page(String guid, String name, List<Content> contents) {
        this.guid = guid;
        this.name = name;
        this.contents = contents;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }
}
