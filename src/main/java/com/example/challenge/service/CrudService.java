package com.example.challenge.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID>{

    T create(T object);

    List<T> findAll();

    Optional<T> findById(ID id);

    Optional<T> update(T object);

    void deleteById(ID id);

}
