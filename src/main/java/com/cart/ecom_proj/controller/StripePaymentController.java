package com.cart.ecom_proj.controller;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/payment")
public class StripePaymentController {

    public StripePaymentController(@Value("${stripe.secret.key}") String secretKey) {
        Stripe.apiKey = secretKey;
    }

    @PostMapping("/create-stripe-session")
    public ResponseEntity<Map<String, String>> createStripeSession(@RequestBody StripeSessionRequest request) throws Exception {
        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
        for (CartItem item : request.getCartItems()) {
            lineItems.add(
                SessionCreateParams.LineItem.builder()
                    .setPriceData(
                        SessionCreateParams.LineItem.PriceData.builder()
                            .setCurrency("inr")
                            .setUnitAmount((long) (item.getPrice() * 100))
                            .setProductData(
                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                    .setName(item.getName())
                                    .build()
                            )
                            .build()
                    )
                    .setQuantity((long) item.getQuantity())
                    .build()
            );
        }
        SessionCreateParams params = SessionCreateParams.builder()
            .setMode(SessionCreateParams.Mode.PAYMENT)
            .setSuccessUrl("http://localhost:5173/success")
            .setCancelUrl("http://localhost:5173/cart")
            .addAllLineItem(lineItems)
            .build();
        Session session = Session.create(params);
        Map<String, String> response = new HashMap<>();
        response.put("url", session.getUrl());
        return ResponseEntity.ok(response);
    }

    public static class StripeSessionRequest {
        private int amount;
        private List<CartItem> cartItems;
        // getters and setters
        public int getAmount() { return amount; }
        public void setAmount(int amount) { this.amount = amount; }
        public List<CartItem> getCartItems() { return cartItems; }
        public void setCartItems(List<CartItem> cartItems) { this.cartItems = cartItems; }
    }
    public static class CartItem {
        private String name;
        private int quantity;
        private int price;
        // getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
        public int getPrice() { return price; }
        public void setPrice(int price) { this.price = price; }
    }
}
