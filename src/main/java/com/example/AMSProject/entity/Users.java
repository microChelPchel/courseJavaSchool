package com.example.AMSProject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class Users implements Serializable {

    @Id
    private String guid;

    @OneToMany(mappedBy="users")
    private List<Viewed> viewed;

    public Users() {
    }

    public Users(String guid, List<Viewed> viewed) {
        this.guid = guid;
        this.viewed = viewed;
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
}
