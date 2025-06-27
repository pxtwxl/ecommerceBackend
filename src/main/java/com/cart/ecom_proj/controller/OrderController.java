package com.cart.ecom_proj.controller;

import com.cart.ecom_proj.dto.OrderCreateRequest;
import com.cart.ecom_proj.dto.OrderResponseDTO;
import com.cart.ecom_proj.model.orders.Order;
import com.cart.ecom_proj.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("buyer/{userId}")
    public List<OrderResponseDTO> getOrdersForBuyer(@PathVariable("userId") int userId) {
        return service.fetchOrders(userId);
    }

    @PostMapping("checkout")
    public void createOrder(@RequestBody OrderCreateRequest request) {
        service.createOrder(request);
    }
}
