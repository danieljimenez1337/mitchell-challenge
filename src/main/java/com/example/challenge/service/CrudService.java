package com.example.challenge.service;

import java.util.List;

public interface CrudService<T, ID>{

    T create(T object);

    List<T> findAll();

    T findById(ID id);

    T update(T object);

    void deleteById(ID id);

}
