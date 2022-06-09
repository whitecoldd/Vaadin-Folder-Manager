package com.example.project.repositories.impl;

import com.example.project.entities.CoreEntity;
import com.example.project.errors.NotFoundException;
import com.example.project.repositories.CoreRepository;
import org.springframework.dao.DataAccessException;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

public abstract class CoreRepositoryImpl<T extends CoreEntity> implements CoreRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;


    public List<T> findAll() {
        return entityManager.createQuery("select n from " + getManagedClass().getSimpleName() + " n", getManagedClass()).getResultList();
    }

    @Transactional
    @Override
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public T update(T entity) throws NotFoundException {

        entityManager.merge(entity);
        return entity;
    }

    @Transactional
    @Override
    public void delete(Long id) throws NotFoundException {
        T storedEntity = findById(id);
        entityManager.remove(storedEntity);
    }

    @Override
    public T findById(Long id) throws NotFoundException {
        T entity = entityManager.find(getManagedClass(), id);
        if (entity == null) {
            throw new NotFoundException();
        }
        return entity;
    }

    protected abstract Class<T> getManagedClass();
}

