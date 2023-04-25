package com.mp.shop.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue
    @Getter
    private Long id;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    private Product product;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
    @Getter
    @Setter
    @Column(nullable = false,name = "quantity")
    private int quantity;

    public CartItem(Product product, User user, int quantity) {
        this.product = product;
        this.user = user;
        this.quantity = quantity;
    }
}
