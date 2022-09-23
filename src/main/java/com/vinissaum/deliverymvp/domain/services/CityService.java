package com.vinissaum.deliverymvp.domain.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vinissaum.deliverymvp.domain.exceptions.EntityInUseException;
import com.vinissaum.deliverymvp.domain.exceptions.ResourceNotFoundException;
import com.vinissaum.deliverymvp.domain.model.City;
import com.vinissaum.deliverymvp.domain.repositories.CityRepository;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    public List<City> findAll() {
        return repository.findAll();
    }

    public City find(Long id) {
        return repository.find(id);
    }

    public City insert(City city) {
        return repository.insert(city);
    }

    public City update(Long id, City city) {
        City cityExists = repository.find(id);

        if (cityExists == null) {
            throw new ResourceNotFoundException(String.format("City id: %d not found", id));
        }
        BeanUtils.copyProperties(city, cityExists, "id");

        return repository.update(cityExists);
    }

    public void delete(Long id) {
        try {
            repository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(String.format("City id: %d not found", id));
        }
        catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("City id: %d can not be removed", id));
        }
    }
}
