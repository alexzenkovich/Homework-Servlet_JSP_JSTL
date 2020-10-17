package persistance;

import java.util.List;

public interface CrudDao<T>{

    T create (T t);

    T getById (long id);

    List<T> getAll();

    void update (T t);

    void delete(long id);
}
