package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

public interface ICRUD<T> {

    void save(T t);
    Optional<T> get(String id);
    void delete(String id);
    Optional<List<T>>findAll();


}
