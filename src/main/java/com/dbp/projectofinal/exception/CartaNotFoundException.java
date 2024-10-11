package com.dbp.projectofinal.exception;

public class CartaNotFoundException extends RuntimeException {
    public CartaNotFoundException(Long id) {
        super("Carta no encontrada con ID: " + id);
    }}
