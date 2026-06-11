package com.jumpstart.foodorderingsystem.exception;

// Custom exceptions are used instead of letting the app crash.
// When a category is not found, this exception is thrown
// and a clean error message is returned to the client.
public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String message) {
        super(message);
    }
}