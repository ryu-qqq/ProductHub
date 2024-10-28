package com.ryuqq.setof.producthub.core.api.controller.v1.validator;

public interface Validator <T> {
    void validate(T value, Object... params);
}
