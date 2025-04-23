package com.infy.AmigoWallet.validation;


import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = RoleValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Score {
}
