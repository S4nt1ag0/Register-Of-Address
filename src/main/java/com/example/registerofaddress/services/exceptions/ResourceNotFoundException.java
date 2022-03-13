package com.example.registerofaddress.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String CPF){
        super("Resource not found. CPF: "+ CPF);
    }
}
