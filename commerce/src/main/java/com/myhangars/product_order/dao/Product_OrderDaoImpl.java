package com.myhangars.product_order.dao;

import com.myhangars.product_order.model.Product_Order;
import com.myhangars.product_order.repository.Product_OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Product_OrderDaoImpl implements Product_OrderDao {

    @Autowired
    private Product_OrderRepository product_orderRepository;

    @Override
    public Optional<Product_Order> findById(long id) {
        return this.product_orderRepository.findById(id);
    }

    @Override
    public Product_Order save(Product_Order product_order) {
        return this.product_orderRepository.save(product_order);
    }
}
