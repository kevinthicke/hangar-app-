package com.myhangars.controller;

import com.myhangars.dto.ProductDto;
import com.myhangars.exception.ApplicationException;
import com.myhangars.exception.ArgumentException;
import com.myhangars.model.Price;
import com.myhangars.model.Product;
import com.myhangars.service.ProductService;
import com.myhangars.service.ProductServiceImpl;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    private Product mockedProduct = new Product();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockedProduct.setId(1);
        mockedProduct.setName("Cocaine");
        mockedProduct.setDescription("A strong stimulant most frequently used as a recreational drug.");

        List<Price> prices = new ArrayList<Price>();
        Arrays.asList(2.99f, 3.99f, 5.99f)
                .forEach(priceQuantity -> {
                    Price price = new Price();
                    price.setPrice(priceQuantity);

                    prices.add(price);
                });

        mockedProduct.setPrices(prices);
        mockedProduct.setState(true);

        when(productService.getById(1)).thenReturn(mockedProduct);
    }

    @Test
    public void testLoadProductById() throws Exception {

        ResponseEntity<ProductDto> result = productController.loadProductById(1);

        ProductDto productDto = new ProductDto();

        productDto.setId(1);
        productDto.setName("Cocaine");
        productDto.setDescription("A strong stimulant most frequently used as a recreational drug.");

        List<Price> prices = new ArrayList<Price>();
        Arrays.asList(2.99f, 3.99f, 5.99f)
                .forEach(priceQuantity -> {
                    Price price = new Price();
                    price.setPrice(priceQuantity);

                    prices.add(price);
                });

        productDto.setPrices(prices);

        ResponseEntity<ProductDto> expected = new ResponseEntity<ProductDto>(
                productDto,
                HttpStatus.OK
        );

        assertThat(result, sameBeanAs(expected));

    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme