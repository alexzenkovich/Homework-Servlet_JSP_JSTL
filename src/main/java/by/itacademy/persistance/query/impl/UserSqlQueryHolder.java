package by.itacademy.persistance.query.impl;

import by.itacademy.persistance.query.JdbcSqlQueryHolder;

public class UserSqlQueryHolder implements JdbcSqlQueryHolder {

    private static final String SELECT_USER_BY_ID = "select id, name, surname, email, age from users where id = ?";
    private static final String SELECT_ALL_USERS = "select id, name, surname, email, age from users";
    private static final String UPDATE_USER_BY_ID = "update users set name = ?, surname = ?, email = ?, age = ? where id = ?";
    private static final String CREATE_USER = "insert into users (name, surname, email, age) values (?,?,?,?)";
    private static final String DELETE_USER_BY_ID = "delete from users where id = ?";

    @Override
    public String getByIdSql() {
        return SELECT_USER_BY_ID;
    }

    @Override
    public String getAllSql() {
        return SELECT_ALL_USERS;
    }

    @Override
    public String updateSql() {
        return UPDATE_USER_BY_ID;
    }

    @Override
    public String createSql() {
        return CREATE_USER;
    }

    @Override
    public String deleteSql() {
        return DELETE_USER_BY_ID;
    }
}
