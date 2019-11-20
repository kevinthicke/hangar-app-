package com.myhangars.products_hangar.builder;

import com.myhangars.products_hangar.model.Products_Hangar;

public class Product_HangarBuilder {
    private Products_Hangar products_hangar;

    public Product_HangarBuilder(long productId, long hangarId) {
        this.products_hangar = new Products_Hangar();
        this.products_hangar.setHangar_id(hangarId);
        this.products_hangar.setProduct_id(productId);
    }

    public Product_HangarBuilder(long productId, long hangarId, int quantity) {
        this(productId, hangarId);
        this.products_hangar.setQuantity(quantity);
    }

    public Products_Hangar getProducts_hangar() {
        return products_hangar;
    }
}
