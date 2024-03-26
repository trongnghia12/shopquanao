package com.example.ShopOnism.Domain;

public class CartDomain {
    private Integer id;
    private Integer id_users;
    private Integer total;
    private Integer type;
    private String date;

    public CartDomain(Integer id, Integer id_users, Integer total, Integer type, String date) {
        this.id = id;
        this.id_users = id_users;
        this.total = total;
        this.type = type;
        this.date = date;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getId_users() { return id_users; }
    public void setId_users(Integer id_users) { this.id_users = id_users; }

    public Integer getTotal() { return total; }
    public void setTotal(Integer total) { this.total = total; }

    public Integer getType() { return type; }
    public void setType(Integer type) { this.type = type;}

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
