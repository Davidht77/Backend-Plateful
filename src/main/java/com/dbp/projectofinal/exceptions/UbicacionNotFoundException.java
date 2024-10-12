package com.dbp.projectofinal.exceptions;

public class UbicacionNotFoundException extends RuntimeException {
    public UbicacionNotFoundException(String message) {
        super("Ubicacion no encontrada."+message);
    }
}
