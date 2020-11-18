package by.itacademy.persistance.statement.impl;

import by.itacademy.model.users.User;
import by.itacademy.persistance.statement.StatementInitializer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserStatementInitializer implements StatementInitializer<User> {
    @Override
    public void createQueryStatement(PreparedStatement prStmt, User user) throws SQLException {
        prStmt.setString(1, user.getName());
        prStmt.setString(2,user.getSurname());
        prStmt.setString(3, user.getEmail());
        prStmt.setInt(4, user.getAge());
    }

    @Override
    public void updateQueryStatement(PreparedStatement prStmt, User user) throws SQLException {
        prStmt.setString(1, user.getName());
        prStmt.setString(2,user.getSurname());
        prStmt.setString(3, user.getEmail());
        prStmt.setInt(4, user.getAge());
    }
}
