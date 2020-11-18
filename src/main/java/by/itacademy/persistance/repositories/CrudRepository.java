package by.itacademy.persistance.repositories;

import java.util.List;

public interface CrudRepository<T>{

    void create (T t);

    T getById (long id);

    List<T> getAll();

    T update (T t);

    void delete(long id);
}
