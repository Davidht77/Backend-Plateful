package com.dbp.projectofinal.exception;

public class PlatoNotFoundException extends RuntimeException {
    public PlatoNotFoundException(String message) {
        super("Plato no encontrado."+message);
    }
}
