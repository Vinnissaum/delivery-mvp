package com.vinissaum.deliverymvp.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vinissaum.deliverymvp.domain.exceptions.EntityInUseException;
import com.vinissaum.deliverymvp.domain.exceptions.ResourceNotFoundException;
import com.vinissaum.deliverymvp.domain.model.State;
import com.vinissaum.deliverymvp.domain.repositories.StateRepository;

@Service
public class StateService {

    private static final String NOT_FOUND_MSG = "State id: %d not found";

    @Autowired
    private StateRepository repository;

    public List<State> findAll() {
        return repository.findAll();
    }

    public State find(Long id) {
        Optional<State> entity = repository.findById(id);

        return entity.orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id)));
    }

    public State insert(State state) {
        return repository.save(state);
    }

    public State update(Long id, State state) {
        Optional<State> entity = repository.findById(id);

        if (entity.isEmpty()) {
            throw new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id));
        }
        BeanUtils.copyProperties(state, entity, "id");

        return repository.save(entity.get());
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id));
        }
        catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("State id: %d can not be removed", id));
        }
    }
}
