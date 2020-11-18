package by.itacademy.model.users;

import by.itacademy.model.basket.Basket;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"authenticate", "role", "basket"})
@ToString(exclude = {"authenticate", "role", "basket"})

@Entity
@Table
public class User {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String surname;
    private String email;
    private int age;

    @OneToOne
    private Authenticate authenticate;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToOne
    private Basket basket;

    public User(String name, String surname, String email, int age) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
    }

    public void addAuthenticate(Authenticate authenticate) {
        this.setAuthenticate(authenticate);
        authenticate.setUser(this);
    }

    public void removeAuthenticate(Authenticate authenticate) {
        this.setAuthenticate(null);
        authenticate.setUser(null);
    }

    public void addBasket(Basket basket) {
        this.setBasket(basket);
        basket.setUser(this);
    }
}
