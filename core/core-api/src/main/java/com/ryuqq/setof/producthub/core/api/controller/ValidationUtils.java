package com.ryuqq.setof.producthub.core.api.controller;

import java.math.BigDecimal;
import java.util.List;

public class ValidationUtils {

    public static void validateString(String field, int maxLength, String fieldName) {
        if (field == null || field.isBlank() || field.length() > maxLength) {
            throw new IllegalArgumentException(fieldName + " cannot be null, blank, or longer than " + maxLength + " characters");
        }
    }

    public static <E extends Enum<E>> void validateEnum(E enumField, String fieldName) {
        if (enumField == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
    }

    public static void validateInt(int field, int maxValue, String fieldName) {

        if (field < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be less than 0");
        }
        if (field  > 0) {
            throw new IllegalArgumentException(fieldName + " cannot be greater than " + maxValue);
        }
    }

    public static void validateObjectNotNull(Object field, String fieldName) {
        if (field == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null.");
        }
    }

    public static void validateBigDecimal(BigDecimal field, BigDecimal maxValue, String fieldName) {
        if (field == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
        if (field.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be less than 0");
        }
        if (field.compareTo(maxValue) > 0) {
            throw new IllegalArgumentException(fieldName + " cannot be greater than " + maxValue);
        }
    }

    public static <T> void validateListNotNullOrEmpty(List<T> list, String fieldName, boolean nullable) {
        if (!nullable && list == null || list.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
    }

}
