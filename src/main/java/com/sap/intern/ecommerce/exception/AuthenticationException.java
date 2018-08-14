package com.sap.intern.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NON_AUTHORITATIVE_INFORMATION)
public class AuthenticationException extends RuntimeException {

    private static final String FORBIDDEN = "Forbidden";

    public AuthenticationException() {
        super(FORBIDDEN);
    }

    public AuthenticationException(String s) {
        super(s);
    }

    public AuthenticationException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public AuthenticationException(Throwable throwable) {
        super(throwable);
    }

    public AuthenticationException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
