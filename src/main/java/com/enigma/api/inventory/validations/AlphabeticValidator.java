package com.enigma.api.inventory.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AlphabeticValidator implements ConstraintValidator<Alphabetic, String> {

    private Alphabetic alphabetic;


//    @Override
//    public void initialize(Alphabetic constraintAnnotation) {
//        ConstraintValidator.super.initialize();
//    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s == null || s.matches("^[ a-zA-Z]+$");
    }


}
