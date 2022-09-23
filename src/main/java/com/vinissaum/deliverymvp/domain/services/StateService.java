package com.vinissaum.deliverymvp.domain.services;

import java.util.List;

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

    @Autowired
    private StateRepository repository;

    public List<State> findAll() {
        return repository.findAll();
    }

    public State find(Long id) {
        return repository.find(id);
    }

    public State insert(State state) {
        return repository.insert(state);
    }

    public State update(Long id, State state) {
        State stateExists = repository.find(id);

        if (stateExists == null) {
            throw new ResourceNotFoundException(String.format("City id: %d not found", id));
        }
        BeanUtils.copyProperties(state, stateExists, "id");

        return repository.update(stateExists);
    }

    public void delete(Long id) {
        try {
            repository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(String.format("State id: %d not found", id));
        }
        catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("State id: %d can not be removed", id));
        }
    }
}
