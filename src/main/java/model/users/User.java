package model.users;

import lombok.*;
import model.basket.Basket;

@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder

public class User {
    private long id;
    private String name;
    private String surname;
    private String email;
    private int age;
    private Authenticate authenticate;
    private Role role;
    private Basket basket;

    public User(String name, String surname, String email, int age, Authenticate authenticate, Role role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
        this.authenticate = authenticate;
        this.role = role;
    }
}
