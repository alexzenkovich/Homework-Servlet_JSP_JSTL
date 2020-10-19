package persistance.query.impl;

import model.users.Authenticate;
import persistance.query.JdbcSqlQueryHolder;

public class AuthenticateSqlQueryHolder implements JdbcSqlQueryHolder {

    private static final String SELECT_AUTHENTICATE_BY_ID = "select id, login, password, profile_enable " +
            "from authenticate where id = ?";
    private static final String SELECT_ALL_AUTHENTICATES = "select id, login, password, profile_enable " +
            "from authenticate";
    private static final String UPDATE_AUTHENTICATE_BY_ID = "update authenticate set login = ?, password = ?, " +
            "profile_enable = ? where id = ?";
    private static final String CREATE_AUTHENTICATE = "insert into authenticate (login, password, profile_enable) " +
            "values (?,?,?)";
    private static final String DELETE_AUTHENTICATE_BY_ID = "delete from authenticate where id = ?";

    @Override
    public String getByIdSql() {
        return SELECT_AUTHENTICATE_BY_ID;
    }

    @Override
    public String getAllSql() {
        return SELECT_ALL_AUTHENTICATES;
    }

    @Override
    public String updateSql() {
        return UPDATE_AUTHENTICATE_BY_ID;
    }

    @Override
    public String createSql() {
        return CREATE_AUTHENTICATE;
    }

    @Override
    public String deleteSql() {
        return DELETE_AUTHENTICATE_BY_ID;
    }
}
