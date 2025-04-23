package com.infy.AmigoWallet.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class ScoreValidator implements ConstraintValidator<Score, String> {

    private static final Set<Character> scoreChars = Set.of('Y', 'G', 'L');

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
//        if (s.length() != 5)
//            return false;
//
//        for (char c : s.toCharArray()) {
//            if (!scoreChars.contains(c)) {
//
//            }
//        }
        return true;
    }
}
