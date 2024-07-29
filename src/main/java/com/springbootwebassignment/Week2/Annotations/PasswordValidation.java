package com.springbootwebassignment.Week2.Annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE,
        ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE_USE})
@Constraint(validatedBy = {PasswordValidator.class})
public @interface PasswordValidation {
    String message() default "Passoword must Contain uppercase, lowercase, special Characters and Numeric Values";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
