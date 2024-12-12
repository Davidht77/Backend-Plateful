package com.dbp.projectofinal.exceptions;

public class PropietarioNotFoundException extends RuntimeException {
    public PropietarioNotFoundException(String message) {
      super("Propietario no encontrado"+message);
    }
}
