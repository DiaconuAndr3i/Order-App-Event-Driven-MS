package com.springboot.controller;

import com.springboot.dto.Order;
import com.springboot.kafka.PlaceOrderProducer;
import com.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private PlaceOrderProducer placeOrderProducer;

    @Autowired
    private OrderService orderService;

    @GetMapping("/test")
    public ResponseEntity<String> testApi(){
        return ResponseEntity.ok("Looks good!");
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody Order order){
        placeOrderProducer.sendMessageToBroker(this.orderService.getOrderEvent(order));

        return ResponseEntity.ok("Order placed Successfully");
    }
}
