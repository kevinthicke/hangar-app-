package com.myhangars.order.service;

import com.myhangars.config.JwtTokenUtil;
import com.myhangars.model.Price;
import com.myhangars.model.UserEntity;
import com.myhangars.order.dao.OrderDao;
import com.myhangars.order.model.Order;
import com.myhangars.product_order.service.Product_OrderService;
import com.myhangars.products_hangar.model.Products_Hangar;
import com.myhangars.products_hangar.service.Products_HangarService;
import com.myhangars.service.ProductService;
import com.myhangars.user.service.UserEntityService;
import com.myhangars.user_profile.service.UserProfileService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class OrderServiceImplTest_ {
    @Mock
    OrderDao orderDao;
    @Mock
    Product_OrderService product_orderService;
    @Mock
    Products_HangarService products_hangarService;
    @Mock
    ProductService productService;
    @Mock
    UserEntityService userEntityService;
    @Mock
    UserProfileService userProfileService;
    @Mock
    JwtTokenUtil jwtTokenUtil;
    @InjectMocks
    OrderServiceImpl orderServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws Exception {
        when(orderDao.findAll()).thenReturn(Arrays.<Order>asList(new Order()));

        List<Order> result = orderServiceImpl.getAll();
        Assert.assertEquals(Arrays.<Order>asList(new Order()), result);
    }

    @Test
    public void testGetAllByUserToken() throws Exception {
        when(orderDao.findByUserEntity(any())).thenReturn(Arrays.<Order>asList(new Order()));
        when(userEntityService.getByUsername(anyString())).thenReturn(new UserEntity());
        when(jwtTokenUtil.getUsernameFromToken(anyString())).thenReturn("getUsernameFromTokenResponse");

        List<Order> result = orderServiceImpl.getAllByUserToken("userToken");
        Assert.assertEquals(Arrays.<Order>asList(new Order()), result);
    }

    @Test
    public void testGetById() throws Exception {
        when(orderDao.findById(anyLong())).thenReturn(null);

        Order result = orderServiceImpl.getById(0L);
        Assert.assertEquals(new Order(), result);
    }

    @Test
    public void testInsert() throws Exception {
        when(orderDao.save(any())).thenReturn(new Order());
        when(products_hangarService.getHangarProduct(anyLong(), anyLong())).thenReturn(new Products_Hangar());
        when(products_hangarService.setQuantityByHangarIdAndProductId(anyLong(), anyLong(), anyInt())).thenReturn(new Products_Hangar());
        when(productService.getLastPrice(anyLong())).thenReturn(new Price(0f));
        when(userEntityService.findById(anyLong())).thenReturn(new UserEntity());
        when(userEntityService.getByUsername(anyString())).thenReturn(new UserEntity());
        when(jwtTokenUtil.getUsernameFromToken(anyString())).thenReturn("getUsernameFromTokenResponse");

        Order result = orderServiceImpl.insert(new Order(), "token");
        Assert.assertEquals(new Order(), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme