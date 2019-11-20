package com.myhangars.product_order.repository;

import com.myhangars.product_order.model.Product_Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Product_OrderRepository extends JpaRepository<Product_Order, Long> {
    public Optional<Product_Order> findById(long id);
}
