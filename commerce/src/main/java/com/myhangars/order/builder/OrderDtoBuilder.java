package com.myhangars.order.builder;

import com.myhangars.order.dto.OrderDto;
import com.myhangars.order.model.Order;

public class OrderDtoBuilder {

    private OrderDto orderDto;

    public OrderDtoBuilder(Order order) {
        this.orderDto = new OrderDto();

        this.orderDto.setId(order.getId());
        this.orderDto.setProduct_orders(order.getProduct_orders());
        this.orderDto.setTotalPrice(order.getTotalPrice());
  //      this.orderDto.setDate(order.getDate());
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }
}
