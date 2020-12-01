package by.it_academy.model.users;

import by.it_academy.model.basket.Basket;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Map;

@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"authenticate", "role", "basket"})
@ToString(exclude = {"authenticate", "role", "basket"})

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String surname;
    private String email;
    private int age;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "authenticate_id", referencedColumnName = "id")
    private Authenticate authenticate;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
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
}
