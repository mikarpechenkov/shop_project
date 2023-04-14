package com.mp.shop.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Entity
@Table(name = "images")
public class Image {
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
    private String location;

    public Image(String name, String location) {
        this.name = name;
        this.location = location;
    }
}
