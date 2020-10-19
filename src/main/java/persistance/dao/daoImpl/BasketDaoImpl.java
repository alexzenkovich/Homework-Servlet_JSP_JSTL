package persistance.dao.daoImpl;

import model.basket.Basket;
import persistance.dao.AbstractCrudDao;
import persistance.mapper.ResultSetMapper;
import persistance.query.JdbcSqlQueryHolder;
import persistance.statement.StatementInitializer;

public class BasketDaoImpl extends AbstractCrudDao<Basket> {


    @Override
    protected JdbcSqlQueryHolder getSqlHolder() {

    }

    @Override
    protected ResultSetMapper<Basket> getResultSetMapper() {
        return null;
    }

    @Override
    protected StatementInitializer<Basket> getStatementInitializer() {
        return null;
    }
}
