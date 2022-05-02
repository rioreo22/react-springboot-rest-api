package com.example.gccoffee.model;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@ToString
public class Order {
    private final UUID orderId;
    private final Email email;
    private final List<OrderItem> orderItems;
    private final LocalDateTime createdAt;
    private OrderStatus orderStatus;
    private String address;
    private String postcode;
    private LocalDateTime updatedAt;

    public Order(UUID orderId, Email email, String address, String postcode, List<OrderItem> orderItems, OrderStatus orderStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.email = email;
        this.orderItems = orderItems;
        this.createdAt = createdAt;
        this.orderStatus = orderStatus;
        this.address = address;
        this.postcode = postcode;
        this.updatedAt = updatedAt;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        this.updatedAt = LocalDateTime.now();
    }

    public void setAddress(String address) {
        this.address = address;
        this.updatedAt = LocalDateTime.now();

    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
        this.updatedAt = LocalDateTime.now();
    }

}
