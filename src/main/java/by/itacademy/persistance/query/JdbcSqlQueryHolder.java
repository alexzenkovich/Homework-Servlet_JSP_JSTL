package by.itacademy.persistance.query;

public interface JdbcSqlQueryHolder {
    String getByIdSql();
    String getAllSql();
    String updateSql();
    String createSql();
    String deleteSql();
}
