package by.itacademy.persistance.dao;

import by.itacademy.exception.ApplicationBaseException;
import by.itacademy.persistance.mapper.ResultSetMapper;
import by.itacademy.persistance.query.JdbcSqlQueryHolder;
import by.itacademy.persistance.statement.StatementInitializer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCrudDao<T> extends AbstractJdbcDao implements CrudDao<T> {

    protected abstract JdbcSqlQueryHolder getSqlHolder();

    protected abstract ResultSetMapper<T> getResultSetMapper();

    protected abstract StatementInitializer<T> getStatementInitializer();

    @Override
    public void create(T t) {
        try (Connection con = getConnector().getConnection();
             PreparedStatement prStmt = con.prepareStatement(getSqlHolder().createSql(), Statement.RETURN_GENERATED_KEYS)) {

            getStatementInitializer().createQueryStatement(prStmt, t);
            prStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error process create entity: " + e.getMessage());
        }
    }

    @Override
    public T getById(long id) {
        try (Connection con = getConnector().getConnection();
             PreparedStatement prStmt = con.prepareStatement(getSqlHolder().getByIdSql())) {
            prStmt.setLong(1, id);

            try (ResultSet rs = prStmt.executeQuery()){
                if (rs.next()) {
                    return getResultSetMapper().processResultSet(rs);
                }
                throw new ApplicationBaseException("Invalid entity id: " + id);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ApplicationBaseException("Error process getById entity method: " + e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error receive database by.itacademy.connection: " + e.getMessage());
        }
    }

    @Override
    public List<T> getAll() {
        List<T> t = new ArrayList<>();
        try (Connection con = getConnector().getConnection();
            PreparedStatement prStmt = con.prepareStatement(getSqlHolder().getAllSql())){
            try (ResultSet rs = prStmt.executeQuery()){
                while (rs.next()) {
                    t.add(getResultSetMapper().processResultSet(rs));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ApplicationBaseException("Error process getAll entities method: " + e.getMessage());
            }

        }catch (SQLException e) {
            e.printStackTrace();
//            throw new ApplicationBaseException("Error receive database by.itacademy.connection: " + e.getMessage());
        }
        return t;
    }

    @Override
    public T update(T t) {
        try (Connection con = getConnector().getConnection();
            PreparedStatement prStmt = con.prepareStatement(getSqlHolder().updateSql())){
            getStatementInitializer().updateQueryStatement(prStmt, t);
            prStmt.executeUpdate();
            return t;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error process update entity method: " + e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        try (Connection con = getConnector().getConnection();
            PreparedStatement prStmt = con.prepareStatement(getSqlHolder().deleteSql())){

            prStmt.setLong(1, id);
            prStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error process delete entity method: " + e.getMessage());
        }
    }
}
