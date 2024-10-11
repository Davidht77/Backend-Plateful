package com.dbp.projectofinal.exception;

public class UbicacionNotFoundException extends RuntimeException {
    public UbicacionNotFoundException(String message) {
        super("Ubicacion no encontrada."+message);
    }
}
