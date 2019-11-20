package com.myhangars.product_order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "PRODUCT_ORDER")
@Getter @Setter
@NoArgsConstructor
public class Product_Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "hangar_id")
    private long hangar_id;

    @Column(name = "product_id")
    private long product_id;

    @Column(name = "quantity")
    private int quantity;

}
