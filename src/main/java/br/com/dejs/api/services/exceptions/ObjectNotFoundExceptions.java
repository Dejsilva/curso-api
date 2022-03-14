package br.com.dejs.api.services.exceptions;

public class ObjectNotFoundExceptions extends RuntimeException{
    public ObjectNotFoundExceptions(String message) {
        super(message);
    }
}
