package com.infy.AmigoWallet.controller;

import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalAdvice {
//    @ExceptionHandler(SignatureException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public String handleExpiredToken(SignatureException e) {
//        return "jwt token invalid/expired please sign in again";
//    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidRole(ConstraintViolationException e) {
        return "Invalid role";
    }
}
