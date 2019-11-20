package com.myhangars.product_order.service;

import com.myhangars.exception.GenericException;
import com.myhangars.product_order.dao.Product_OrderDao;
import com.myhangars.product_order.model.Product_Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Product_OrderServiceImpl implements Product_OrderService {

    @Autowired
    private Product_OrderDao product_orderDao;

    @Override
    public Product_Order getById(long id) {
        return this.product_orderDao
                .findById(id)
                .orElseThrow(() -> new GenericException.NotFound(id));
    }

    @Override
    public Product_Order insert(Product_Order product_order) {
        return this.product_orderDao
                .save(product_order);
    }
}
