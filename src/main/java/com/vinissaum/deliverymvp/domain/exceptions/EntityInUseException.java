package com.vinissaum.deliverymvp.domain.exceptions;

public class EntityInUseException extends RuntimeException {

    public EntityInUseException(String message) {
        super(message);
    }

}
