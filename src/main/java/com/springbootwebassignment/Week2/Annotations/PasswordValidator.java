package com.springbootwebassignment.Week2.Annotations;

import com.springbootwebassignment.Week2.Exceptions.ResourceNotFoundException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {

    public boolean PatternAnalyzer(String regex, String password){
        Pattern patterMatcher = Pattern.compile(regex);
        Matcher hasMatcher = patterMatcher.matcher(password);

        return hasMatcher.find();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if(password==null)
            throw new ResourceNotFoundException("Password Cannot be Null");

        if (password.length() < 10)
            return false;

        return PatternAnalyzer("[A-Z]", password) && PatternAnalyzer("[a-z]", password) && PatternAnalyzer("[^a-zA-Z0-9]", password);
    }
}
