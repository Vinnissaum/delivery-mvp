package com.vinissaum.deliverymvp.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vinissaum.deliverymvp.domain.model.Restaurant;
import com.vinissaum.deliverymvp.domain.repositories.RestaurantRepository;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext
    private EntityManager manager;

    public List<Restaurant> findAll() {
        return manager.createQuery("from Restaurant", Restaurant.class)
            .getResultList();
    }

    public Restaurant find(Long id) {
        return manager.find(Restaurant.class, id);
    }

    @Transactional
    public Restaurant insert(Restaurant restaurant) {
        return manager.merge(restaurant);
    }

    @Transactional
    public Restaurant update(Restaurant restaurant) {
        return manager.merge(restaurant);
    }

    @Transactional
    public void delete(Long id) {
        Restaurant restaurant = manager.find(Restaurant.class, id);
        manager.remove(restaurant);
    }

}
