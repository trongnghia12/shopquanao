package com.example.ShopOnism.Domain;

import java.io.Serializable;

public class CategoryDomain implements Serializable {
    String id;
    String title;
    String pic;

    public CategoryDomain(String id, String title, String pic) {
        this.id = id;
        this.title = title;
        this.pic = pic;
    }

    public CategoryDomain() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
