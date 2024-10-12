package com.dbp.projectofinal.exceptions;

public class ComentarioNotFoundException extends RuntimeException {
    public ComentarioNotFoundException(String message) {
        super("Comentario no encontrado.");
    }
}
