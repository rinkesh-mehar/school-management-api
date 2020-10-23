package com.rinkesh.schoolmanagementapi.exceptions;

public class InactiveUserException extends RuntimeException {

    public InactiveUserException(String message) {
	super(message);
    }
}
