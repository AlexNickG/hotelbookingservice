package com.example.hotelbookingservice.validation;

import com.example.hotelbookingservice.dto.HotelFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;


public class HotelFilterValidValidator implements ConstraintValidator<HotelFilterValid, HotelFilter> {

    @Override
    public boolean isValid(HotelFilter value, ConstraintValidatorContext context) {
        if (ObjectUtils.anyNull(value.getPageNumber(), value.getPageSize())) {
            return false;
        }
        return true;
    }
}
