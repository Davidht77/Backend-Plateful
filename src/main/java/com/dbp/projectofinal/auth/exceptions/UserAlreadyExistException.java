package org.e2e.e2e.auth.exceptions;


public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {super(message);}
}
