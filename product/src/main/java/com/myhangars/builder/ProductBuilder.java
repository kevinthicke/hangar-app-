package com.myhangars.builder;


import com.myhangars.dto.ProductDto;
import com.myhangars.model.Product;

public class ProductBuilder {

    private Product product;

    public ProductBuilder(ProductDto productDto) {

        this.product = new Product();
        this.product.setName(productDto.getName());
        this.product.setDescription(productDto.getDescription());
        this.product.setPrices(productDto.getPrices());

    }

    public Product getProduct() {
        return product;
    }
}
