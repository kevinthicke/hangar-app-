package com.myhangars.product_order.service;

import com.myhangars.product_order.model.Product_Order;

import java.util.Optional;

public interface Product_OrderService {

    public Product_Order getById(long id);
    public Product_Order insert(Product_Order product_order);
}
