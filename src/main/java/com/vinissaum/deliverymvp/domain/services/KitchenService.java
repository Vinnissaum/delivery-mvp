package com.vinissaum.deliverymvp.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vinissaum.deliverymvp.domain.exceptions.EntityInUseException;
import com.vinissaum.deliverymvp.domain.exceptions.ResourceNotFoundException;
import com.vinissaum.deliverymvp.domain.model.Kitchen;
import com.vinissaum.deliverymvp.domain.repositories.KitchenRepository;

@Service
public class KitchenService {

    @Autowired
    private KitchenRepository repository;

    public List<Kitchen> findAll() {
        return repository.findAll();
    }

    public Kitchen find(Long id) {
        return repository.find(id);
    }

    public Kitchen insert(Kitchen kitchen) {
        return repository.insert(kitchen);
    }

    public Kitchen update(Kitchen kitchen) {
        return repository.update(kitchen);
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
