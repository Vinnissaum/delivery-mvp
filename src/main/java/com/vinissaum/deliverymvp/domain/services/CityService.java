package com.vinissaum.deliverymvp.domain.services;

import java.util.List;
import java.util.Optional;

import com.vinissaum.deliverymvp.domain.exceptions.StateNotFoundException;
import com.vinissaum.deliverymvp.domain.model.State;
import com.vinissaum.deliverymvp.domain.repositories.StateRepository;
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

    private static final String NOT_FOUND_MSG = "City id: %d not found";

    @Autowired
    private CityRepository repository;

    @Autowired
    private StateRepository stateRepository;

    public List<City> findAll() {
        return repository.findAll();
    }

    public City find(Long id) {
        Optional<City> entity = repository.findById(id);
        return entity.orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id)));
    }

    public City insert(City city) {
        return repository.save(city);
    }

    public City update(Long id, City city) {
        Long stateId = city.getState().getId();

        City cityEntity = repository.findById(id).orElseThrow(() -> //
            new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id)));
        BeanUtils.copyProperties(city, cityEntity, "id");

        State state = stateRepository.findById(stateId).orElseThrow(() -> new StateNotFoundException(stateId));

        cityEntity.setState(state);

        return repository.save(cityEntity);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id));
        }
        catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("City id: %d can not be removed", id));
        }
    }
}
