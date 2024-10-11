package com.dbp.projectofinal.exception;

public class PropietarioNotFoundException extends RuntimeException {
    public PropietarioNotFoundException(String message) {
      super("Propietario no encontrado"+message);
    }
}
