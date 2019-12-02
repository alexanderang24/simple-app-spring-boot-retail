package com.example.retailapplication.exception;

/**
 * Exception to handle conflicted data related to UserService, such as an
 * attempt to log in when user is already logged in and registration
 * with username and password that was already registered.
 */
public class UserConflictException extends RetailApplicationException {

    /**
     * Constructor with custom message
     * @param message to show error message
     */
    public UserConflictException(String message) {
        super(message);
    }
}
