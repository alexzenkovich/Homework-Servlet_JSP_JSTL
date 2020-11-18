package by.itacademy.persistance.mapper.impl;

import by.itacademy.model.users.User;
import by.itacademy.persistance.mapper.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResultSetMapper implements ResultSetMapper<User> {

    @Override
    public User processResultSet(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .surname(rs.getString("surname"))
                .email(rs.getString("email"))
                .age(rs.getInt("age"))
                .build();
    }
}
