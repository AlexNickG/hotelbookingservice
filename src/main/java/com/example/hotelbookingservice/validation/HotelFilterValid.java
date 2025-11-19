package com.example.hotelbookingservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = HotelFilterValidValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
public @interface HotelFilterValid {
    String message() default "Поля пагинации должны быть указаны.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
