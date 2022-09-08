package com.springboot.service;

import com.springboot.dto.Order;
import com.springboot.event.OrderEvent;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
    public OrderEvent getOrderEvent(Order order){
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setOrder(order);
        orderEvent.setStatus("Order placed");
        order.setIdBuyer(generateId());
        return orderEvent;
    }

    public String generateId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
