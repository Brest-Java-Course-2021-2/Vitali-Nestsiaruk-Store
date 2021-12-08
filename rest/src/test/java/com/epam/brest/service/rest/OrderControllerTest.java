package com.epam.brest.service.rest;

import com.epam.brest.model.Order;
import com.epam.brest.service.OrderService;
import com.epam.brest.service.rest.exception.CustomExceptionHandler;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.regex.Matcher;

import static org.mockito.ArgumentMatchers.anyInt;


@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @Captor
    private ArgumentCaptor<Integer> captorId;

    private MockMvc mockMvc;

    @BeforeEach
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController)
                .setControllerAdvice(new CustomExceptionHandler())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @Test
    public void getOrderById() throws Exception {

        Order order = new Order();
        order.setOrderId(46);
        order.setShipper("Tesla");

        Mockito.when(orderService.getOrderById(anyInt())).thenReturn(order);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/orders/123")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderId", Matchers.is(order.getOrderId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shipper", Matchers.is(order.getShipper())))
        ;

        Mockito.verify(orderService).getOrderById(captorId.capture());
        Integer id = captorId.getValue();
        Assertions.assertEquals(123, id);
    }

    @Test
    public void getOrderByIdException() throws Exception {
        Mockito.when(orderService.getOrderById(anyInt()))
                .thenThrow(new IllegalArgumentException("test message"));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/orders/123")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Handle: test message"))
        ;
    }
}