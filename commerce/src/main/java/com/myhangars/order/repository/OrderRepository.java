package com.myhangars.order.repository;

import com.myhangars.model.UserEntity;
import com.myhangars.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public Optional<Order> findById(long id);

    //@Query(value = "SELECT order FROM Order order WHERE order.userEntity = ?1")
    public List<Order> findByUserEntity(UserEntity userEntity);
}