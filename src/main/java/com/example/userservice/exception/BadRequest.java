package com.example.userservice.exception;

public class BadRequest extends RuntimeException{
    public BadRequest(String description){
        super(description);
    }
}
