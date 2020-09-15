package model;

public class User {
    private long id;
    private String name;
    private String surname;
    private String email;
    private int age;
    private Authenticate authenticate;
    private Role role;

    public User() {
    }

    public User(String name, String surname, String email, int age, Authenticate authenticate, Role role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
        this.authenticate = authenticate;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Authenticate getAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(Authenticate authenticate) {
        this.authenticate = authenticate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", authenticate=" + authenticate +
                ", role=" + role +
                '}';
    }
}
