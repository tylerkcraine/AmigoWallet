package com.infy.AmigoWallet.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class RoleValidator implements ConstraintValidator<Role, String> {

    private final Set<String> roles = Set.of("ROLE_ADMIN", "ROLE_USER");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return roles.contains(value);
    }
}
