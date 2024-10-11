package com.dbp.projectofinal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UsuarioNotFoundException.class)
  public ResponseEntity<String> handleUsuarioNotFound(UsuarioNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(PropietarioNotFoundException.class)
  public ResponseEntity<String> handlePropietarioNotFound(PropietarioNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(RestauranteNotFoundException.class)
  public ResponseEntity<String> handleRestauranteNotFound(RestauranteNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(PlatoNotFoundException.class)
  public ResponseEntity<String> handlePlatoNotFound(PlatoNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ResenaNotFoundException.class)
  public ResponseEntity<String> handleResenaNotFound(ResenaNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UbicacionNotFoundException.class)
  public ResponseEntity<String> handleUbicacionNotFound(UbicacionNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(CartaNotFoundException.class)
  public ResponseEntity<String> handleCartaNotFound(CartaNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ComentarioNotFoundException.class)
  public ResponseEntity<String> handleComentarioNotFound(ComentarioNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }
}
