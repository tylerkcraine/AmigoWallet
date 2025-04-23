package com.infy.AmigoWallet.controller;

import com.infy.AmigoWallet.exception.IncorrectUserPassword;
import com.infy.AmigoWallet.exception.NotSignedInException;
import com.infy.AmigoWallet.exception.UserAlreadyExistException;
import io.jsonwebtoken.security.SignatureException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {AuthController.class})
public class AuthControllerAdvice {

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ApiResponse(responseCode = "409", description = "Conflict - Username already exists in Database")
    public String handleUniqueUserNameValidation(UserAlreadyExistException e) {
        return "User already registered";
    }

    @ExceptionHandler(NotSignedInException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ApiResponse(responseCode = "409", description = "Forbidden - Must be signed in")
    public String handleNotSignedIn(NotSignedInException e) {
        return "Must be signed in to edit a user";
    }

    @ExceptionHandler(IncorrectUserPassword.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400", description = "Bad Request - Username or password incorrect")
    public String handleIncorrectSignIn(IncorrectUserPassword e) {
        return e.getMessage();
    }
}
