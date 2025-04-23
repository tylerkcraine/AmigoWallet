package com.infy.AmigoWallet.controller;

import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = {"com.infy.AmigoWallet.controller", "com.infyAmigoWallet.service", "com.infyAmigoWallet.config"})
public class GlobalAdvice {
    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleExpiredToken(SignatureException e) {
        return "jwt token invalid/expired please sign in again";
    }
}
