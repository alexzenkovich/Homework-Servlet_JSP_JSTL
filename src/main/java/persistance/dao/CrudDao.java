package persistance.dao;

import java.util.List;

public interface CrudDao<T>{

    T create (T t);

    T getById (long id);

    List<T> getAll();

    T update (T t);

    void delete(long id);
}
