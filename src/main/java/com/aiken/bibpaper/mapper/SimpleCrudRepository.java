package com.aiken.bibpaper.mapper;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleCrudRepository<T, ID extends Serializable> {
    // (1)
    List<T> findOne(ID id);

    // (2)
    boolean exists(ID id);

    // (3)
    List<T> findAll();

    // (4)
    Page<T> findAll(Pageable pageable);

    // (5)
    long count();

    // (6)
    T save(T entity);

    // (7)
    void delete(ID id);
}
