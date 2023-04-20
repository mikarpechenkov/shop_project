package com.mp.shop.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;

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
    @Lob
    @JdbcTypeCode(Types.LONGVARBINARY)
    @Column(nullable = false)
    private byte[] content;

    public Image(String name, byte[] content) {
        this.name = name;
        this.content=content;
    }
}
