package com.vinissaum.deliverymvp.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vinissaum.deliverymvp.domain.model.State;
import com.vinissaum.deliverymvp.domain.repositories.StateRepository;

@Component
public class StateRepositoryImpl implements StateRepository {
    @PersistenceContext
    EntityManager manager;

    @Override
    public List<State>  findAll() {
        return manager.createQuery("from State", State.class).getResultList();
    }

    @Override
    public State find(Long id) {
        return manager.find(State.class, id);
    }

    @Transactional
    @Override
    public State insert(State entity) {
        return manager.merge(entity);
    }

    @Transactional
    @Override
    public State update(State entity) {
        return manager.merge(entity);
    }

    @Transactional
    @Override
    public void delete(State entity) {
        State obj = find(entity.getId());
        manager.remove(obj);
    }
}
