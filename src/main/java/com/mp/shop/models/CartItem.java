package com.mp.shop.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cart_items")
public class CartItem {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class PrimaryKey implements Serializable {
        private static final long serialVersionUID = 1234567L;
        @Column(name="product_id", nullable = false)
        private Long productId;
        @Column(name="user_id", nullable = false)
        private Long userId;
    }
    @EmbeddedId
    private PrimaryKey id;
    @ManyToOne
    @MapsId("productId")
    private Product product;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @Column(nullable = false,name = "quantity")
    private int quantity;

    public CartItem(Product product, User user, int quantity) {
        this.id = new PrimaryKey(product.getId(),user.getId());
        this.product = product;
        this.user = user;
        this.quantity = quantity;
    }
}
