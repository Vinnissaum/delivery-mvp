package com.vinissaum.deliverymvp.domain.repositories;

import com.vinissaum.deliverymvp.domain.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> findAll();
    Restaurant find(Long id);
    Restaurant insert(Restaurant restaurant);
    Restaurant update(Restaurant kitchen);
    void delete(Restaurant restaurant);

}
