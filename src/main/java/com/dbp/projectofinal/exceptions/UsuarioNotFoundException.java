package com.dbp.projectofinal.exceptions;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(String message) {
        super("Usuario no encontrado"+message);
    }
}
