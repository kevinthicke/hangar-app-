package com.myhangars.order.builder;

import com.myhangars.order.dto.OrderDto;
import com.myhangars.order.model.Order;

public class OrderBuilder {

    private Order order;

    public OrderBuilder(OrderDto orderDto) {
        this.order = new Order();

        this.order.setId(orderDto.getId());
        this.order.setProduct_orders(orderDto.getProduct_orders());
//        this.order.setDate(orderDto.getDate());
        this.order.setTotalPrice(orderDto.getTotalPrice());
    }

    public Order getOrder() {
        return order;
    }
}
