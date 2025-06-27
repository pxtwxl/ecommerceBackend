package com.cart.ecom_proj.service;

import com.cart.ecom_proj.repo.OrderItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepo repo;
}
