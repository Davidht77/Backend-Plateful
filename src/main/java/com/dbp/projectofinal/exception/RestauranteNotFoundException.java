package com.dbp.projectofinal.exception;

public class RestauranteNotFoundException extends RuntimeException {
    public RestauranteNotFoundException(String message) {
      super("Restaurante no encontrado."+message);
    }
}
