package com.vinissaum.deliverymvp.domain.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinissaum.deliverymvp.domain.exceptions.EntityInUseException;
import com.vinissaum.deliverymvp.domain.exceptions.ResourceNotFoundException;
import com.vinissaum.deliverymvp.domain.model.Kitchen;
import com.vinissaum.deliverymvp.domain.model.Restaurant;
import com.vinissaum.deliverymvp.domain.repositories.KitchenRepository;
import com.vinissaum.deliverymvp.domain.repositories.RestaurantRepository;

@Service
public class RestaurantService {

    private static final String NOT_FOUND_MSG = "Restaurant id: %d not found";

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private KitchenRepository kitchenRespository;

    public List<Restaurant> findAll() {
        return repository.findAll();
    }

    public Restaurant find(Long id) {
        Optional<Restaurant> entity = repository.findById(id);

        return entity.orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id)));
    }

    public Restaurant insert(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        Optional<Kitchen> entity = kitchenRespository.findById(kitchenId);

        if (entity.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Kitchen id: %d not found", kitchenId));
        }

        return repository.save(restaurant);
    }

    public Restaurant update(Long id, Restaurant restaurant) {
        Optional<Restaurant> entity = repository.findById(id);

        if (entity.isEmpty()) {
            throw new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id));
        }
        BeanUtils.copyProperties(restaurant, entity);
        return repository.save(entity.get());
    }

    public Restaurant partialUpdate(Long id, Map<String, Object> attributesToUpdate) {
        Optional<Restaurant> entity = repository.findById(id);

        if (entity.isEmpty()) {
            throw new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id));
        }
        merge(entity.get(), attributesToUpdate);

        return repository.save(entity.get());
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id));
        }
        catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Restaurant id: %d can not be removed", id));
        }
    }

    private void merge(Restaurant entity, Map<String, Object> attributes) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurant updatedRestaurant = objectMapper.convertValue(attributes, Restaurant.class);

        attributes.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Restaurant.class, key);
            field.setAccessible(true);

            Object newValue = ReflectionUtils.getField(field, updatedRestaurant);

            ReflectionUtils.setField(field, entity, newValue);
        });
    }
}
