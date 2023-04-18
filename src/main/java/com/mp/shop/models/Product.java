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
    @Lob
    @Column(nullable = false)
    private String description;
    @Getter
    @Setter
    private BigDecimal price;
    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JoinTable(
            name = "product_image",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> pictures = new HashSet<>();

    public Product(String name, String description, BigDecimal price, Set<Image> pictures) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.pictures.addAll(pictures);
    }

    public Product(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
