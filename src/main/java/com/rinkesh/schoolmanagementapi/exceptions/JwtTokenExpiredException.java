package com.rinkesh.schoolmanagementapi.exceptions;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenExpiredException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public JwtTokenExpiredException(String msg) {
	super(msg);
    }
}