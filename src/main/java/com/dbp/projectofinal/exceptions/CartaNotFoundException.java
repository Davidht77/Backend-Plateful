package com.dbp.projectofinal.exceptions;

public class CartaNotFoundException extends RuntimeException {
    public CartaNotFoundException(Long id) {
        super("Carta no encontrada con ID: " + id);
    }}
