package com.myhangars.product_order.dao;

import com.myhangars.product_order.model.Product_Order;

import java.util.Optional;

public interface Product_OrderDao {

    Optional<Product_Order> findById(long id);
    Product_Order save(Product_Order product_order);
}
