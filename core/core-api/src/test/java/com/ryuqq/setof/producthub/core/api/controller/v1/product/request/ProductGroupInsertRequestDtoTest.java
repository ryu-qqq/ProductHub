package com.ryuqq.setof.producthub.core.api.controller.v1.product.request;

import com.ryuqq.setof.enums.core.ProductCondition;
import com.ryuqq.setof.producthub.data.ProductModuleHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ProductGroupInsertRequestDtoTest {

    @Test
    @DisplayName("ProductGroupInsertRequestDto 필드 유효성 검사 - 유효한 데이터")
    void testValidProductGroupInsertRequestDto() {
        ProductGroupInsertRequestDto productGroupInsertRequestDto = ProductModuleHelper.toProductGroupInsertRequestDto("Test Product", BigDecimal.valueOf(10000), BigDecimal.valueOf(10000), ProductCondition.NEW);
        Assertions.assertDoesNotThrow(productGroupInsertRequestDto::toProductGroupCommand);
    }

//    @Test
//    @DisplayName("ProductGroupInsertRequestDto 필드 유효성 검사 - Product Group Name 길이 초과")
//    void testProductGroupNameTooLong() {
//        Executable executable = () -> ProductModuleHelper.toProductGroupInsertRequestDto("Product Group Name Exceeding the Maximum Length of 100 Characters Product Group Name Exceeding the Maximum Length of 100 Characters",
//                BigDecimal.valueOf(10000), BigDecimal.valueOf(10000), ProductCondition.NEW);
//        Assertions.assertThrows(IllegalArgumentException.class, executable, "Product Group Name cannot exceed 100 characters");
//    }
//
//    @Test
//    @DisplayName("Price 유효성 검사 - Regular Price가 Current Price보다 작은 경우")
//    void testInvalidPriceRelationship() {
//        Executable executable = () -> ProductModuleHelper.toProductGroupInsertRequestDto("Test Product", BigDecimal.valueOf(10000), BigDecimal.valueOf(11000), ProductCondition.NEW);
//        Assertions.assertThrows(IllegalArgumentException.class, executable, "Regular Price must be greater than Current Price");
//    }
//
//    @Test
//    @DisplayName("Enum Value 유효성 검사 - Enum Value가 null일 경우")
//    void testInvalidEnumValue() {
//        Executable executable = () -> ProductModuleHelper.toProductGroupInsertRequestDto("Test Product", BigDecimal.valueOf(10000), BigDecimal.valueOf(11000), null);
//        Assertions.assertThrows(IllegalArgumentException.class, executable, "Product Condition Type cannot be null");
//    }


}