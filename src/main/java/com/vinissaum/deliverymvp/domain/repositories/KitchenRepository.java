package com.vinissaum.deliverymvp.domain.repositories;

import com.vinissaum.deliverymvp.domain.model.Kitchen;

import java.util.List;

public interface KitchenRepository {

    List<Kitchen> findAll();
    Kitchen find(Long id);
    Kitchen insert(Kitchen kitchen);
    Kitchen update(Kitchen kitchen);
    void delete(Kitchen kitchen);

}
