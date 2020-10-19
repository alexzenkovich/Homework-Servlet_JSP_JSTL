package persistance.statement.impl;

import model.users.Authenticate;
import persistance.statement.StatementInitializer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthenticateStatementInitializer implements StatementInitializer<Authenticate> {
    @Override
    public void createQueryStatement(PreparedStatement prStmt, Authenticate authenticate) throws SQLException {
        prStmt.setString(1, authenticate.getLogin());
        prStmt.setString(2, authenticate.getPassword());
        prStmt.setBoolean(3, authenticate.isProfileEnable());
    }

    @Override
    public void updateQueryStatement(PreparedStatement prStmt, Authenticate authenticate) throws SQLException {
        prStmt.setString(1, authenticate.getLogin());
        prStmt.setString(2, authenticate.getPassword());
        prStmt.setBoolean(3, authenticate.isProfileEnable());
    }
}
