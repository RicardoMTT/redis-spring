package com.ricardo.msvc_user.mcvc_user.repositories;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(String id, String entityName) {
        super(String.format("No se encontró %s con id: %s", entityName, id));
    }
}

