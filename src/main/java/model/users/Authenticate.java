package model.users;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder

public class Authenticate {
    private long id;
    private String login;
    private String password;
    private boolean profileEnable;

    public Authenticate(String login, String password) {
        this.login = login;
        this.password = password;
        this.profileEnable = true;
    }
}
