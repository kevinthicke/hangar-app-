package com.myhangars.builder;

import com.myhangars.model.Product;
import com.myhangars.dto.ProductExtendedDto;

public class ProductExtendedDtoBuilder {

    private ProductExtendedDto productExtendedDto;

    public ProductExtendedDtoBuilder(Product product, int quantity) {
        productExtendedDto = new ProductExtendedDto();

        this.productExtendedDto.setId(product.getId());
        this.productExtendedDto.setName(product.getName());
        this.productExtendedDto.setPrices(product.getPrices());
        this.productExtendedDto.setDescription(product.getDescription());
        this.productExtendedDto.setQuantity(quantity);
    }

    public ProductExtendedDto getProductExtended() {
        return this.productExtendedDto;
    }
}
