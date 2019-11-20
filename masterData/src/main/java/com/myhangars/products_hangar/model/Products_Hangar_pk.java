package com.myhangars.products_hangar.model;

import java.io.Serializable;

public class Products_Hangar_pk implements Serializable {

    private static final long serialVersionUID = -851696929159570973L;

    private long hangar_id;
    private long product_id;

    public Products_Hangar_pk() {}

    public Products_Hangar_pk(long hangar, long product) {
        this.hangar_id = hangar;
        this.product_id = product;
    }

    public long getHangar() {
        return hangar_id;
    }

    public void setHangar(long hangar) {
        this.hangar_id = hangar;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Products_Hangar_pk that = (Products_Hangar_pk) o;

        if (hangar_id != that.hangar_id) return false;
        return product_id == that.product_id;
    }

    @Override
    public int hashCode() {
        int result = (int) (hangar_id ^ (hangar_id >>> 32));
        result = 31 * result + (int) (product_id ^ (product_id >>> 32));
        return result;
    }
}

