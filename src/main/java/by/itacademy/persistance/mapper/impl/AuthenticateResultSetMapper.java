package by.itacademy.persistance.mapper.impl;

import by.itacademy.model.users.Authenticate;
import by.itacademy.persistance.mapper.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticateResultSetMapper implements ResultSetMapper<Authenticate> {
    @Override
    public Authenticate processResultSet(ResultSet rs) throws SQLException {
        return Authenticate.builder()
                .id(rs.getLong("id"))
                .login(rs.getString("login"))
                .password(rs.getString("password"))
                .profileEnable(rs.getBoolean("profile_enable"))
                .build();
    }
}
