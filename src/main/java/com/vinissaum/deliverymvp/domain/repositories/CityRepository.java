package com.vinissaum.deliverymvp.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinissaum.deliverymvp.domain.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {}
