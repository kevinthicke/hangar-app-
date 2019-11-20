package com.myhangars.products_hangar.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Products_Hangar_pk.class)
@Table(name = "PRODUCTS_HANGAR")
public class Products_Hangar implements Serializable {

    @Id
    @Column(name = "hangar_id")
    private long hangar_id;

    @Id
    @Column(name = "product_id")
    private long product_id;

    @Column(name = "quantity")
    private int quantity = 1;

    public long getHangar_id() {
        return hangar_id;
    }

    public void setHangar_id(long hangar_id) {
        this.hangar_id = hangar_id;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}