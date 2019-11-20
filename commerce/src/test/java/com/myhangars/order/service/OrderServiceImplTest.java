package com.myhangars.order.service;

import com.myhangars.config.JwtTokenUtil;
import com.myhangars.exception.OrderException;
import com.myhangars.model.Price;
import com.myhangars.model.Product;
import com.myhangars.model.UserEntity;
import com.myhangars.order.dao.OrderDao;
import com.myhangars.order.model.Order;
import com.myhangars.product_order.model.Product_Order;
import com.myhangars.product_order.service.Product_OrderService;
import com.myhangars.products_hangar.exception.GenericException;
import com.myhangars.products_hangar.model.Products_Hangar;
import com.myhangars.products_hangar.service.Products_HangarService;
import com.myhangars.service.ProductService;
import com.myhangars.user.service.UserEntityService;
import com.myhangars.user_profile.service.UserProfileService;
import org.hamcrest.Matchers;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Array;
import java.util.*;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {

    @Mock OrderDao orderDao;
    @Mock Product_OrderService product_orderService;
    @Mock Products_HangarService products_hangarService;
    @Mock ProductService productService;
    @Mock UserEntityService userEntityService;
    @Mock UserProfileService userProfileService;
    @Mock JwtTokenUtil jwtTokenUtil;
    @InjectMocks OrderServiceImpl orderServiceImpl;

    @Before
    public void setUp() {

        List<Product_Order> product_ordersMocked = Collections.<Product_Order>singletonList(new Product_Order());
        product_ordersMocked.get(0).setQuantity(1);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws Exception {
        when(orderDao.findAll()).thenReturn(Collections.<Order>singletonList(new Order()));

        List<Order> ordersResult = orderServiceImpl.getAll();
        List<Order> ordersExpected = Collections.<Order>singletonList(new Order());

        Assert.assertThat(
                ordersResult,
                SamePropertyValuesAs.samePropertyValuesAs(ordersExpected)
        );
    }

    @Test
    public void testGetAllByUserToken() throws Exception {

        when(jwtTokenUtil.getUsernameFromToken("Bearer token")).thenReturn("username");
        when(userEntityService.getByUsername("username")).thenReturn(new UserEntity());
        when(orderDao.findByUserEntity(any())).thenReturn(Collections.<Order>singletonList(new Order()));

        List<Order> result = orderServiceImpl.getAllByUserToken("userToken");

        Assert.assertThat(
                Collections.<Order>singletonList(new Order()),
                sameBeanAs(result)
        );
    }

   @Test
    public void testGetById() throws Exception {
        when(orderDao.findById(anyLong())).thenReturn(Optional.of(new Order()));

        Order result = orderServiceImpl.getById(0L);
        Assert.assertThat(new Order(), sameBeanAs(result));
    }

    @Test(expected = OrderException.UnableModifyQuantityProducts_Hangar.class)
    public void testInsert_ThrowsNotEnoughtStockException() throws Exception {

        when(orderDao.save(any())).thenReturn(new Order());

        Products_Hangar products_hangarResponse = new Products_Hangar();
        products_hangarResponse.setQuantity(0);

        when(products_hangarService.getHangarProduct(anyLong(), anyLong())).thenReturn(products_hangarResponse);

    }

    /*
    TODO: Test errors (?)
    @Test(expected = GenericException.NotFound.class)
    public void testInsert_ThrowsGenericException_NotFound() {
        when(this.products_hangarService.getHangarProduct(anyLong(), anyLong())).thenThrow(GenericException.NotFound.class);
        this.orderServiceImpl.insert(new Order(), "token");
    }
    */

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme