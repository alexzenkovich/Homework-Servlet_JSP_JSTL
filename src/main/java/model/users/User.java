package model.users;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import model.basket.Basket;

@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString

public class User {
    private long id;
    private String name;
    private String surname;
    private String email;
    private int age;
    private Authenticate authenticate;
    private Role role;
    private final Basket basket = new Basket();

    public User(String name, String surname, String email, int age, Authenticate authenticate, Role role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
        this.authenticate = authenticate;
        this.role = role;
    }
}
