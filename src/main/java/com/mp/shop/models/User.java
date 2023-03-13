package com.mp.shop.models;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Getter @Setter @Column(nullable = false)
    private String name;
    @Getter @Setter @Column(nullable = false)
    private String surname;
    @Getter @Setter @Column(nullable = false)
    private String emailAddress;
    @Getter @Setter @Column(nullable = false)
    private String password;

    public User(String name, String surname, String emailAddress, String password) {
        this.name = name;
        this.surname = surname;
        this.emailAddress = emailAddress;
        this.password = password;
    }
}
