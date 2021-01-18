package by.it_academy.model.users;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String login;
    private String password;
    private boolean profileEnable;

    @OneToOne(mappedBy = "authenticate", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private User user;

    public Authenticate(String login, String password) {
        this.login = login;
        this.password = password;
        this.profileEnable = true;
    }
}