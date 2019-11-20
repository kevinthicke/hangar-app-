package com.myhangars.order.dao;

import com.myhangars.model.UserEntity;
import com.myhangars.order.model.Order;
import com.myhangars.product_order.model.Product_Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    public List<Order> findAll();
    public List<Order> findByUserEntity(UserEntity userEntity);
    public Optional<Order> findById(long id);
    public Order save(Order order);

}
