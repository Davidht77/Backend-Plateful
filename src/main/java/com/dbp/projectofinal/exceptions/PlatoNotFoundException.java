package com.dbp.projectofinal.exceptions;

public class PlatoNotFoundException extends RuntimeException {
    public PlatoNotFoundException(String message) {
        super("Plato no encontrado."+message);
    }
}
