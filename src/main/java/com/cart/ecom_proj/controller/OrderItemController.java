package com.cart.ecom_proj.controller;

import com.cart.ecom_proj.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/orderitems")
public class OrderItemController {

    @Autowired
    private OrderItemService service;


}
