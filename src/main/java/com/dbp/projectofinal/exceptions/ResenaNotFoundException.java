package com.dbp.projectofinal.exceptions;

public class ResenaNotFoundException extends RuntimeException {
    public ResenaNotFoundException(String message) {
        super("Resena no encontrada: " + message);
    }
}
