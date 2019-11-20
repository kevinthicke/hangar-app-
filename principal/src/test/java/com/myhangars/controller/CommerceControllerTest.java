package com.myhangars.controller;

import com.myhangars.order.dto.OrderDto;
import com.myhangars.order.model.Order;
import com.myhangars.order.service.OrderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class CommerceControllerTest {

    @Mock
    OrderService orderService;
    @Mock
    HttpServletRequest httpServletRequest;
    @InjectMocks
    CommerceController commerceController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws Exception {
        when(orderService.getAllByUserToken(anyString())).thenReturn(Arrays.<Order>asList(new Order()));

        ResponseEntity<?> result = commerceController.getAll("tokenExtended");
        Assert.assertNull(result);
    }

    @Test
    public void testInsert() throws Exception {
        when(orderService.insert(any(), anyString())).thenReturn(new Order());

        ResponseEntity<?> result = commerceController.insert(new OrderDto(), "tokenExtended");
        Assert.assertNull(result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme