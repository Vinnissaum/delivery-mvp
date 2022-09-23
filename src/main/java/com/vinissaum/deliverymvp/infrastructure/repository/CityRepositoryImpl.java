package com.vinissaum.deliverymvp.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vinissaum.deliverymvp.domain.model.City;
import com.vinissaum.deliverymvp.domain.repositories.CityRepository;

@Component
public class CityRepositoryImpl implements CityRepository {
    @PersistenceContext
    EntityManager manager;

    @Override
    public List<City>  findAll() {
        return manager.createQuery("from City", City.class).getResultList();
    }

    @Override
    public City find(Long id) {
        return manager.find(City.class, id);
    }

    @Transactional
    @Override
    public City insert(City entity) {
        return manager.merge(entity);
    }

    @Transactional
    @Override
    public City update(City entity) {
        return manager.merge(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        City obj = find(id);
        manager.remove(obj);
    }
}
