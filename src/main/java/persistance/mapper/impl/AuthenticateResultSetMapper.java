package persistance.mapper.impl;

import model.users.Authenticate;
import persistance.mapper.ResultSetMapper;

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
