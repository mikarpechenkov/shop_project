package com.mp.shop.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@NoArgsConstructor
@ToString
@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue
    @Getter
    private Long id;
    @Getter @Setter
    @Column(nullable = false)
    private String name;
    @Getter @Setter
    @Transient
    private Set<User> users;

    public Role(String name, Set<User> users) {
        this.name = name;
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
