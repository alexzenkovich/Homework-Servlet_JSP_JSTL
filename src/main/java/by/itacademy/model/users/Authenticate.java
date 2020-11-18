package by.itacademy.model.users;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = "user")
@ToString(exclude = "user")

@Entity
@Table
public class Authenticate {
    @Id
    @GeneratedValue
    private long id;

    private String login;
    private String password;
    private boolean profileEnable;

    @OneToOne
    private User user;

    public Authenticate(String login, String password) {
        this.login = login;
        this.password = password;
        this.profileEnable = true;
    }
}
