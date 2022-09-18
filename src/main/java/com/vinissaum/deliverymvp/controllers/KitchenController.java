package com.vinissaum.deliverymvp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vinissaum.deliverymvp.domain.model.Kitchen;
import com.vinissaum.deliverymvp.domain.repositories.KitchenRepository;

@RestController
@RequestMapping(value = "/kitchens", produces = MediaType.APPLICATION_JSON_VALUE)
public class KitchenController {

    @Autowired
    KitchenRepository repository;

    @GetMapping
    public List<Kitchen> index() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Kitchen show(@PathVariable Long id) {
        return repository.find(id);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public Kitchen store(@RequestBody Kitchen kitchen) {

        return repository.insert(kitchen);
    }
}
