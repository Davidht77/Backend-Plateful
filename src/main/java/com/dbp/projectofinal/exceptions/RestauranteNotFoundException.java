package com.dbp.projectofinal.exceptions;

public class RestauranteNotFoundException extends RuntimeException {
    public RestauranteNotFoundException(String message) {
      super("Restaurante no encontrado."+message);
    }
}
