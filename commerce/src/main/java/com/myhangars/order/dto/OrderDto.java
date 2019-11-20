package com.myhangars.order.dto;

import com.myhangars.product_order.model.Product_Order;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class OrderDto {

    private long id;

    @NotEmpty
    private List<Product_Order> product_orders = new ArrayList<Product_Order>();

    @NotNull @Positive
    private float totalPrice;


}
