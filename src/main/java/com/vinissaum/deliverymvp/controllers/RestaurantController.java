package com.vinissaum.deliverymvp.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vinissaum.deliverymvp.domain.exceptions.EntityInUseException;
import com.vinissaum.deliverymvp.domain.exceptions.ResourceNotFoundException;
import com.vinissaum.deliverymvp.domain.model.Restaurant;
import com.vinissaum.deliverymvp.domain.services.RestaurantService;

@RestController
@RequestMapping(value = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {

    @Autowired
    RestaurantService service;

    @GetMapping
    public ResponseEntity<List<Restaurant>> index() {
        List<Restaurant> list = service.findAll();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> show(@PathVariable Long id) {
        Restaurant entity = service.find(id);

        return ResponseEntity.ok(entity);
    }

    @PostMapping
    public ResponseEntity<?> store(@RequestBody Restaurant restaurant) {
        try {
            Restaurant entity = service.insert(restaurant);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(restaurant.getId()).toUri();

            return ResponseEntity.created(location).body(entity);
        } catch(ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        try {
            Restaurant entity = service.update(id, restaurant);

            return ResponseEntity.ok(entity);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Restaurant> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
