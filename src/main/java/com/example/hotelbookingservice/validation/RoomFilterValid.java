package com.example.hotelbookingservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = RoomFilterValidValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
public @interface RoomFilterValid {
    String message() default "Если вы указываете дату заезда, вы должны указать также дату выезда.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
