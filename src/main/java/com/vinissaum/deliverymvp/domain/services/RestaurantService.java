package com.vinissaum.deliverymvp.domain.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vinissaum.deliverymvp.domain.exceptions.EntityInUseException;
import com.vinissaum.deliverymvp.domain.exceptions.ResourceNotFoundException;
import com.vinissaum.deliverymvp.domain.model.Kitchen;
import com.vinissaum.deliverymvp.domain.model.Restaurant;
import com.vinissaum.deliverymvp.domain.repositories.KitchenRepository;
import com.vinissaum.deliverymvp.domain.repositories.RestaurantRepository;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private KitchenRepository kitchenRespository;

    public List<Restaurant> findAll() {
        return repository.findAll();
    }

    public Restaurant find(Long id) {
        return repository.find(id);
    }

    public Restaurant insert(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchenExists = kitchenRespository.find(kitchenId);

        if (kitchenExists == null) {
            throw new ResourceNotFoundException(String.format("Kitchen id: %d not found", kitchenId));
        }

        return repository.insert(restaurant);
    }

    public Restaurant update(Long id, Restaurant restaurant) {
        Restaurant restaurantExists = repository.find(id);

        if (restaurantExists == null) {
            throw new ResourceNotFoundException(String.format("Restaurant id: %d not found", id));
        }
        BeanUtils.copyProperties(restaurant, restaurantExists, "id");

        return repository.update(restaurantExists);
    }

    public void delete(Long id) {
        try {
            repository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(String.format("Kitchen id: %d not found", id));
        }
        catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Kitchen id: %d can not be removed", id));
        }
    }
}
