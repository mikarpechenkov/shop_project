package com.mp.shop.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Getter @Setter @Column(nullable = false)
    private String name;
    @Getter @Setter @Column(nullable = false)
    private String surname;
    @Getter @Setter @Column(nullable = false)
    private String emailAddress;
    @Getter @Setter @Column(nullable = false)
    private String password;
    @Getter @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User(String name, String surname, String emailAddress, String password) {
        this.name = name;
        this.surname = surname;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return getEmailAddress();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
