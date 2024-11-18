package com.ryuqq.setof.api.core.controller.v1.product.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductGroupCommandValidator.class)
@Documented
public @interface ProductGroupCommandValidate {
    String message() default "Invalid Product Group Command Request";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
