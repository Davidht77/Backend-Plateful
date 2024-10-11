package com.dbp.projectofinal.exception;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(String message) {
        super("Usuario no encontrado"+message);
    }
}
