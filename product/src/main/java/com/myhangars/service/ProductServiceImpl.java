package com.myhangars.service;


import com.myhangars.dao.ProductDao;
import com.myhangars.exception.ApplicationException;
import com.myhangars.exception.ApplicationExceptionCause;
import com.myhangars.model.Price;
import com.myhangars.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    public Page<Product> getAll(Pageable pageable) {

        Page<Product> products = this.productDao.findAll(pageable);

        if(products.isEmpty())
            throw new ApplicationException(ApplicationExceptionCause.PRODUCT_NOT_FOUND);

        return products;
    }

    public Product getById(long id) {

        return this.productDao
                .findById(id)
                .orElseThrow(() -> new ApplicationException(ApplicationExceptionCause.PRODUCT_NOT_FOUND));
    }

    @Override
    public Product getByName(String name) {
        return this.productDao
                .findByName(name)
                .orElseThrow(() -> new ApplicationException(ApplicationExceptionCause.PRODUCT_NOT_FOUND));
    }

    @Override
    public Product insert(Product product) {
        return this.productDao.save(product);
    }

    @Override
    public Product update(long id, Product productUpdated) {

        Product product = this.getById(id);

        product.setName(productUpdated.getName());
        product.setDescription(productUpdated.getDescription());

        float lastPrice = this.getLastPrice(id).getPrice();
        float newPrice = productUpdated.getPrices().get(0).getPrice();

        if (lastPrice != newPrice) {
            this.insertPrice(id, newPrice);
        }

        return this.productDao.save(product);
    }

    @Override
    public Product insertPrices(long id, List<Price> prices) {
        Product product = this.getById(id);

        product.setPrices(prices);

        return this.insert(product);

    }

    @Override
    public Product insertPrice(long productId, float price) {

        Product product = this.getById(productId);

        List<Price> productPrice = product.getPrices();
        productPrice.add(new Price(price));

        return this.productDao.save(product);
    }

    @Override
    public Price getLastPrice(long productId) {

        List<Price> prices = this.getById(productId).getPrices();
        int length = prices.size();

        return prices.get(length - 1);

    }
}
