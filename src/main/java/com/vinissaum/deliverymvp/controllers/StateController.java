package com.vinissaum.deliverymvp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vinissaum.deliverymvp.domain.model.State;
import com.vinissaum.deliverymvp.domain.repositories.StateRepository;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository repository;

    @GetMapping
    public List<State> index() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public State show(@PathVariable Long id) {
        return repository.find(id);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public State store(@RequestBody State state) {

        return repository.insert(state);
    }
}
