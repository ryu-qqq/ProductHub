package com.ryuqq.setof.storage.db.core;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * 1 -> true
 * 0 or null -> false
 */
@Converter(autoApply = true)
public class BooleanToIntegerConverter implements AttributeConverter<Boolean, Integer> {


    @Override
    public Integer convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? 1 : 0;
    }

    @Override
    public Boolean convertToEntityAttribute(Integer dbData) {
        return dbData != null && dbData == 1;
    }
}
