package persistance.dao.daoImpl;

import model.users.Authenticate;
import model.users.User;
import persistance.dao.AbstractCrudDao;
import persistance.mapper.ResultSetMapper;
import persistance.mapper.impl.AuthenticateResultSetMapper;
import persistance.query.JdbcSqlQueryHolder;
import persistance.query.impl.AuthenticateSqlQueryHolder;
import persistance.statement.StatementInitializer;
import persistance.statement.impl.AuthenticateStatementInitializer;

import java.util.List;

public class AuthenticateDaoImpl extends AbstractCrudDao<Authenticate> {

    private final JdbcSqlQueryHolder authJdbcSqlQueryHolder;
    private final StatementInitializer<Authenticate> authStatementInitializer;
    private final ResultSetMapper<Authenticate> authResultSetMapper;

    public AuthenticateDaoImpl() {
        authJdbcSqlQueryHolder = new AuthenticateSqlQueryHolder();
        authStatementInitializer = new AuthenticateStatementInitializer();
        authResultSetMapper = new AuthenticateResultSetMapper();
    }

    @Override
    public Authenticate create (Authenticate authenticate) {
        return super.create(authenticate);
    }

    @Override
    public Authenticate getById (long id) {
        return super.getById(id);
    }

    @Override
    public List<Authenticate> getAll() {
        return super.getAll();
    }

    @Override
    public Authenticate update(Authenticate authenticate) {
        return super.update(authenticate);
    }

    @Override
    public void delete(long id) {
        super.delete(id);
    }

    @Override
    protected JdbcSqlQueryHolder getSqlHolder() {
        return authJdbcSqlQueryHolder;
    }

    @Override
    protected ResultSetMapper<Authenticate> getResultSetMapper() {
        return authResultSetMapper;
    }

    @Override
    protected StatementInitializer<Authenticate> getStatementInitializer() {
        return authStatementInitializer;
    }
}
