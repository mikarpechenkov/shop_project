package com.mp.shop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;

@Data
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Lob
    @JdbcTypeCode(Types.LONGVARBINARY)
    @Column(nullable = false)
    private byte[] content;

    public Image(String name, byte[] content) {
        this.name = name;
        this.content = content;
    }
}
