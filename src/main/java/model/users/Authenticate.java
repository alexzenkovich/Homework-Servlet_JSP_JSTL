package model.users;

import lombok.*;

@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder

public class Authenticate {
    private long id;
    private String login;
    private String password;
    private boolean profileEnable;

    public Authenticate(String login, String password, boolean profileEnable) {
        this.login = login;
        this.password = password;
        this.profileEnable = profileEnable;
    }
}
