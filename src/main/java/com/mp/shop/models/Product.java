package com.mp.shop.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@ToString
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue
    @Getter
    private Long id;
    @Getter
    @Setter
    @Column(nullable = false)
    private String name;
    @Getter
    @Setter
    @Column(nullable = false)
    private String description;
    @Getter
    @Setter
    private BigDecimal price;
    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Set<Image> pictures;

    public Product(String name, String description, BigDecimal price, Set<Image> pictures) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.pictures = new HashSet<>(pictures);
    }

    public Product(String name, String description, BigDecimal price, Image image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.pictures = new HashSet<>();
        pictures.add(image);
    }
}
