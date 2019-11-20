package com.myhangars.model;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "PRICE")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "PRICE")
    private float price;

    public Price() {}

    public Price(float price) {
        this.price = price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice(){
        return this.price;
    }

    public long getId() {
        return this.id;
    }
}
