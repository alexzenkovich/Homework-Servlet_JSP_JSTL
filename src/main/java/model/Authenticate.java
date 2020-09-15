package model;

public class Authenticate {
    private String login;
    private String password;
    private boolean profileEnable;

    public Authenticate() {
    }

    public Authenticate(String login, String password, boolean profileEnable) {
        this.login = login;
        this.password = password;
        this.profileEnable = profileEnable;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isProfileEnable() {
        return profileEnable;
    }

    public void setProfileEnable(boolean profileEnable) {
        this.profileEnable = profileEnable;
    }

    @Override
    public String toString() {
        return "Authenticate{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", profileEnable=" + profileEnable +
                '}';
    }
}
