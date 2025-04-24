package com.infy.AmigoWallet.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = RoleValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Role {
    String message() default "not a role";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
