package com.vinissaum.deliverymvp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinissaum.deliverymvp.domain.model.Kitchen;
import com.vinissaum.deliverymvp.domain.repositories.KitchenRepository;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    KitchenRepository repository;

    @GetMapping
    public List<Kitchen> index() {
        return repository.findAll();
    }
}
