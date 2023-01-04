package com.vinissaum.deliverymvp.domain.exceptions;

public class StateNotFoundException extends ResourceNotFoundException {
    public StateNotFoundException(String message) {
        super(message);
    }

    public StateNotFoundException(Long stateId) {
        this(String.format("State with Id: %d not found", stateId));
    }
}
