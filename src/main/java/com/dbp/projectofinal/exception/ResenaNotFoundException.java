package com.dbp.projectofinal.exception;

public class ResenaNotFoundException extends RuntimeException {
    public ResenaNotFoundException(String message) {
        super("Resena no encontrada: " + message);
    }
}
