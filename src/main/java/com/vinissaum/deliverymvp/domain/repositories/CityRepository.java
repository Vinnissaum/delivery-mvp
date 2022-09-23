package com.vinissaum.deliverymvp.domain.repositories;

import com.vinissaum.deliverymvp.domain.model.City;

import java.util.List;

public interface CityRepository {

    List<City> findAll();
    City find(Long id);
    City insert(City city);
    City update(City city);
    void delete(Long id);

}
