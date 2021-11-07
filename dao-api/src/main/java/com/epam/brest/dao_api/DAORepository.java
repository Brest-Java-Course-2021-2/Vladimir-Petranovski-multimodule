package com.epam.brest.dao_api;


import java.util.List;

public interface DAORepository<T> {

    List<T> findAll();

    T findById(Integer id);

    void save(T t);

    void update(Integer id, T t);

    void delete(Integer id);
}
