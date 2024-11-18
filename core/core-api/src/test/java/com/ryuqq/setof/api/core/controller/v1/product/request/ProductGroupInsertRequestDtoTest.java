package com.ryuqq.setof.api.core.controller.v1.product.request;

import com.ryuqq.setof.api.core.controller.v1.product.request.ProductGroupInsertRequestDto;
import com.ryuqq.setof.api.core.data.ProductModuleHelper;
import com.ryuqq.setof.enums.core.ProductCondition;
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

}