package com.myhangars.dto;

import com.myhangars.model.Price;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class ProductExtendedDto {

    private long id;
    //private boolean state = true;
    private String name;
    private String description;
    private List<Price> prices = new ArrayList<Price>();
    private int quantity;

}
