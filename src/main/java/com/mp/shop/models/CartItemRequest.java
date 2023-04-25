package com.mp.shop.models;

import lombok.Data;

@Data
public class CartItemRequest {
    private Long productId;
    private int quantity;
}
