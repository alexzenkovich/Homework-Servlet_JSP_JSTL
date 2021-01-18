package by.it_academy.model.users;

import by.it_academy.model.basket.Basket;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
<<<<<<< HEAD
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
=======
import java.util.*;
>>>>>>> master
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"authenticate", "roles", "basket"})
@ToString(exclude = {"authenticate", "roles", "basket"})

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
    private Set<Role> roles = new HashSet<>();

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
<<<<<<< HEAD
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
=======
        return null;
>>>>>>> master
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