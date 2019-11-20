package com.myhangars.order.dao;

import com.myhangars.model.UserEntity;
import com.myhangars.order.model.Order;
import com.myhangars.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public List<Order> findByUserEntity(UserEntity userEntity) {
        return this.orderRepository.findByUserEntity(userEntity);
    }

    @Override
    public Optional<Order> findById(long id) {
        return this.orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return this.orderRepository.save(order);
    }
}
