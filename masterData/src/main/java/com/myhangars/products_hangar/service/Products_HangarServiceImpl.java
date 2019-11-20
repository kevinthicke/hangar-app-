package com.myhangars.products_hangar.service;

import com.myhangars.service.HangarService;
import com.myhangars.builder.ProductExtendedDtoBuilder;
import com.myhangars.dao.ProductDao;
import com.myhangars.model.Product;
import com.myhangars.dto.ProductExtendedDto;
import com.myhangars.service.ProductService;
import com.myhangars.products_hangar.builder.Product_HangarBuilder;
import com.myhangars.products_hangar.dao.Products_HangarDao;
import com.myhangars.products_hangar.model.Products_Hangar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class Products_HangarServiceImpl implements Products_HangarService {

    @Autowired
    private Products_HangarDao products_hangarDao;

    //TODO: replace ProductDao by ProductService :)
    @Autowired
    private ProductDao productDao;

    @Autowired
    private HangarService hangarService;

    @Autowired
    private ProductService productService;

    private Stream<ProductExtendedDto> buildListProductExtended(Stream<Product> productStream, long hangarId) {

        return productStream.map((Product product) -> {

            long productId = product.getId();

            return this.getListProducts_HangarsByHangarId(hangarId)
                    .stream()
                    .filter(products_hangar -> products_hangar.getProduct_id() == productId)
                    .map(products_hangar -> new ProductExtendedDtoBuilder(product, products_hangar.getQuantity())
                            .getProductExtended())
                    .findFirst().orElse(null);
        });
    }

    @Override
    public List<Products_Hangar> getListProducts_HangarsByHangarId(long id) {
        this.hangarService.getById(id);

        return this.products_hangarDao.findListProducts_HangarByHangarId(id);
    }

    @Override
    public List<Products_Hangar> getListProducts_HangarsByProductId(long id) {
        this.productService.getById(id);

        return this.products_hangarDao.findListProducts_HangarByProductId(id);
    }

    @Override
    public List<ProductExtendedDto> getHangarProductsByHangarId(long hangarId) {

        this.hangarService.getById(hangarId);

        Stream<Product> products = this.products_hangarDao
                                        .findListProducts_HangarByHangarId(hangarId)
                                        .stream()
                                        .map(Products_Hangar::getProduct_id)
                                        .map(productId -> this.productDao.findById(productId).get());

        return buildListProductExtended(products, hangarId).collect(Collectors.toList());
    }

    @Override
    public Products_Hangar getHangarProduct(long hangarId, long productId) {
        return this.products_hangarDao
                .findByHangarIdAndProductId(hangarId, productId)
                .orElseThrow(() -> new com.myhangars.products_hangar.exception.GenericException.NotFound(hangarId, productId));
    }

    @Override
    public List<ProductExtendedDto> getHangarProductByHangarName(String name) {
        long hangarId = this.hangarService.getFirstByName(name).getId();

        return this.getHangarProductsByHangarId(hangarId);
    }


    @Override
    public Products_Hangar includeProductInHangarByHangarId(Product product, long hangarId, int quantity) {

        Products_Hangar products_hangar = new Product_HangarBuilder(product.getId(), hangarId, quantity)
                .getProducts_hangar();

        return this.products_hangarDao.includeProductInHangar(products_hangar);
    }

    @Override
    public Products_Hangar includeProductInHangarByHangarIdAndProductId(long hangarId, long productId) {

        // Compruebo que existen ambos id ...
        long _hangarId = this.hangarService.getById(hangarId).getId();
        long _productId = this.productService.getById(productId).getId();

        Products_Hangar products_hangar = new Product_HangarBuilder(productId, hangarId).getProducts_hangar();

        return this.products_hangarDao.includeProductInHangar(products_hangar);
    }

    @Override
    public Products_Hangar setQuantityByHangarIdAndProductId(long hangarId, long productId, int quantity) {

        Products_Hangar products_hangar = this.products_hangarDao
                .findByHangarIdAndProductId(hangarId, productId)
                .orElseThrow(() -> new com.myhangars.products_hangar.exception.GenericException.NotFound(hangarId, productId));

        products_hangar.setQuantity(quantity);

        return this.products_hangarDao.save(products_hangar);
    }
}
