package com.project.order_service.controller;

import com.project.order_service.model.Order;
import com.project.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")   //For Placing new Order
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.placeOrder(order));
    }

    @GetMapping("/user/{email}")   // Get order by User
    public ResponseEntity<List<Order>> getOrdersByUser(
            @PathVariable String email) {
        return ResponseEntity.ok(
                orderService.getOrdersByUser(email));
    }
    @GetMapping("/all")                  // Get all orders
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(
                orderService.getAllOrders());
    }
    @PutMapping("/status/{id}")             //Update order status
    public ResponseEntity<Order> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(
                orderService.updateOrderStatus(id, status));
    }
}
