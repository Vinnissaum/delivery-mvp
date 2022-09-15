package com.vinissaum.deliverymvp.domain.repositories;

import com.vinissaum.deliverymvp.domain.model.State;

import java.util.List;

public interface StateRepository {

    List<State> findAll();
    State find(Long id);
    State insert(State state);
    State update(State state);
    void delete(State state);

}
