package com.example.ShopOnism.Domain;

public class CommentDomain {
    Integer id, id_user;
    String  content, username;
    String datecmt;

    public CommentDomain(Integer id, Integer id_user, String content, String username, String datecmt) {
        this.id = id;
        this.id_user = id_user;
        this.content = content;
        this.username = username;
        this.datecmt = datecmt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
