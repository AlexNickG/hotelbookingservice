package com.example.hotelbookingservice.validation;

import com.example.hotelbookingservice.dto.UserFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;

public class UserFilterValidValidator implements ConstraintValidator<UserFilterValid, UserFilter> {
    @Override
    public boolean isValid(UserFilter value, ConstraintValidatorContext context) {
        if (ObjectUtils.anyNull(value.getPageNumber(), value.getPageSize())) {
            return false;
        }
        return true;
    }
}
