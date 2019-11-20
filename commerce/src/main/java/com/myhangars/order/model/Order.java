package com.myhangars.order.model;

import com.myhangars.model.UserEntity;
import com.myhangars.product_order.model.Product_Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDER_COMMERCE")
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "products")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product_Order> product_orders = new ArrayList<Product_Order>();

    @Column(name = "total_price")
    private float totalPrice;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(name = "user_id_fk")
    )
    private UserEntity userEntity;

}
