package by.it_academy.model.users;

import by.it_academy.model.basket.Basket;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"authenticate", "role", "basket"})
@ToString(exclude = {"authenticate", "role", "basket"})

@Entity
@Table(name = "usrs")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String email;
    private int age;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Authenticate authenticate;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private List<Role> role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)

    private Basket basket;

    public User(String name, String surname, String email, int age) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
    }

    public void addAuthenticate(Authenticate authenticate) {
        authenticate.setUser(this);
        this.setAuthenticate(authenticate);
    }

    public void deleteAuthenticate(Authenticate authenticate) {
        authenticate.setUser(null);
        this.setAuthenticate(null);
    }

    public void addBasket(Basket basket) {
        basket.setUser(this);
        this.setBasket(basket);
    }

    public void deleteBasket(Basket basket) {
        basket.setUser(null);
        this.setBasket(null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ;

    }

    @Override
    public String getPassword() {
        return authenticate.getPassword();
    }

    @Override
    public String getUsername() {
        return authenticate.getLogin();
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
        return authenticate.isProfileEnable();
    }
}