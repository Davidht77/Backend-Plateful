package com.dbp.projectofinal.exception;

public class ComentarioNotFoundException extends RuntimeException {
    public ComentarioNotFoundException(String message) {
        super("Comentario no encontrado.");
    }
}
