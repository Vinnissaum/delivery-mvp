package com.vinissaum.deliverymvp.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vinissaum.deliverymvp.domain.model.Kitchen;
import com.vinissaum.deliverymvp.domain.repositories.KitchenRepository;

@Component
public class KitchenRepositoryImpl implements KitchenRepository {
    @PersistenceContext
    EntityManager manager;

    @Override
    public List<Kitchen>  findAll() {
        return manager.createQuery("from Kitchen", Kitchen.class).getResultList();
    }

    @Override
    public Kitchen find(Long id) {
        return manager.find(Kitchen.class, id);
    }

    @Transactional
    @Override
    public Kitchen insert(Kitchen entity) {
        return manager.merge(entity);
    }

    @Transactional
    @Override
    public Kitchen update(Kitchen entity) {
        return manager.merge(entity);
    }

    @Transactional
    @Override
    public void delete(Kitchen entity) {
        Kitchen obj = find(entity.getId());
        manager.remove(obj);
    }
}
