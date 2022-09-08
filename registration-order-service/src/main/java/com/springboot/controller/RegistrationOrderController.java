package com.springboot.controller;

import com.springboot.dto.Order;
import com.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class RegistrationOrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/saveOrder")
    public ResponseEntity<String> saveOrder(@RequestBody Order order) throws ExecutionException, InterruptedException {
        orderService.saveOrder(order);
        return ResponseEntity.ok("Order saved successfully");
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Order>> getAllOrders() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/getOrderByIdBuyer/{idBuyer}")
    public ResponseEntity<List<Order>> getOrderByIdBuyer(@PathVariable String idBuyer) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(orderService.getOrderDetailsByIdBuyer(idBuyer));
    }

    @DeleteMapping("/deleteOrder/{idOrder}")
    public ResponseEntity<String> deleteOrderById(@PathVariable String idOrder){
        return ResponseEntity.ok(orderService.deleteOrderByIdOrder(idOrder));
    }
}
