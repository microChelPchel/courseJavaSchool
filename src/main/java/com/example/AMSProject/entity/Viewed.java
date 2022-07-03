package com.example.AMSProject.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "viewed")
public class Viewed implements Serializable {

    @Id
    private String guid;

    @ManyToOne
    @JoinColumn(name="user_guid", nullable=false)
    private Users users;


    @ManyToOne
    @JoinColumn(name="content_guid", nullable=false)
    private Content content;

    public Viewed() {
    }

    public Viewed(String guid, Users users, Content content) {
        this.guid = guid;
        this.users = users;
        this.content = content;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
