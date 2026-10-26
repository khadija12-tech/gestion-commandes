package ma.fstt.service;

import java.util.List;

public interface IDao<T> {
    void save(T obj);
    void update(T obj);
    void delete(int id);
    T findById(int id);
    List<T> findAll();
}