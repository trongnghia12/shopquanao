package com.example.ShopOnism.Domain;

public class DetailCartDomain {
    private Integer id;
    private Integer id_cart;
    private Integer id_product;
    private Integer num;

    public DetailCartDomain(Integer id, Integer id_cart, Integer id_category, Integer num) {
        this.id = id;
        this.id_cart = id_cart;
        this.id_product = id_category;
        this.num = num;
    }

    public DetailCartDomain() {
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getId_cart() { return id_cart; }
    public void setId_cart(Integer id_cart) { this.id = id_cart; }

    public Integer getId_product() { return id_product; }
    public void setId_product(Integer id_product) { this.id_product = id_product; }

    public Integer getNum() { return num; }
    public void setNum(Integer num) { this.num = num; }
}
