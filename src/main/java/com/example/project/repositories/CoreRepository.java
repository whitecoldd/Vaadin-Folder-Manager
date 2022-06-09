package com.example.project.repositories;

import com.example.project.entities.CoreEntity;
import com.example.project.errors.NotFoundException;


import java.util.List;

public interface CoreRepository<T extends CoreEntity> {

    List<T> findAll();

    T save(T entity);

    T update(T entity) throws NotFoundException;

    void delete(Long id) throws NotFoundException;

    T findById(Long id) throws NotFoundException;
}
