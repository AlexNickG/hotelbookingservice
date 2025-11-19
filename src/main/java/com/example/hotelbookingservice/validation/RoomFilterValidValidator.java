package com.example.hotelbookingservice.validation;

import com.example.hotelbookingservice.dto.RoomFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;


public class RoomFilterValidValidator implements ConstraintValidator<RoomFilterValid, RoomFilter> {

    @Override
    public boolean isValid(RoomFilter value, ConstraintValidatorContext context) {
//        if (ObjectUtils.anyNull(value.getPageNumber(), value.getPageSize())) {
//            return false;
//        }

        return ObjectUtils.allNull(value.getOccupancyStart(), value.getOccupancyEnd())
                || ObjectUtils.allNotNull(value.getOccupancyStart(), value.getOccupancyEnd());
    }
}
