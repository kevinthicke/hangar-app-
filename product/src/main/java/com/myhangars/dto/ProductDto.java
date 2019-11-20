package com.myhangars.dto;


import com.myhangars.model.Price;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter @Setter
public class ProductDto {

    private long id;

    @NotEmpty @NotBlank
    private String name;

    @NotEmpty @NotBlank
    private String description;

    @NotEmpty
    private List<Price> prices = new ArrayList<Price>();

}