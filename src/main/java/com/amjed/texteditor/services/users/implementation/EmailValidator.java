package com.amjed.texteditor.services.users.implementation;

import com.amjed.texteditor.annotations.ValidEmail;
import com.amjed.texteditor.constants.Constants;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private Pattern pattern;
    private Matcher matcher;

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context){
        return (validateEmail(email));
    }
    private boolean validateEmail(String email) {
        pattern = Pattern.compile(Constants.EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
