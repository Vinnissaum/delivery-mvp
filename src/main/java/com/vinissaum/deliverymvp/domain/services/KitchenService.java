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
import com.vinissaum.deliverymvp.domain.model.Kitchen;
import com.vinissaum.deliverymvp.domain.repositories.KitchenRepository;

@Service
public class KitchenService {

    private static final String NOT_FOUND_MSG = "Kitchen id: %d not found";

    @Autowired
    private KitchenRepository repository;

    public List<Kitchen> findAll() {
        return repository.findAll();
    }

    public Kitchen find(Long id) {
        Optional<Kitchen> entity = repository.findById(id);

        return entity.orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id)));
    }

    public Kitchen insert(Kitchen kitchen) {
        return repository.save(kitchen);
    }

    public Kitchen update(Long id, Kitchen kitchen) {
        Optional<Kitchen> entity = repository.findById(id);

        if (entity.isEmpty()) {
            throw new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id));
        }
        BeanUtils.copyProperties(kitchen, entity, "id");

        return repository.save(entity.get());
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id));
        }
        catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Kitchen id: %d can not be removed", id));
        }
    }
}
