package com.ryuqq.setof.producthub.core.api.controller.v1.product.request;

import com.ryuqq.setof.producthub.data.ProductModuleHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductGroupCommandContextRequestDtoTest {


    @Test
    @DisplayName("ProductGroupCommandContextRequestDto 필드 유효성 검사 - 유효한 데이터")
    void testValidProductGroupInsertRequestDto() {
        ProductGroupCommandContextRequestDto productGroupCommandContextRequestDto = ProductModuleHelper.toProductGroupCommandContextRequestDto();
        Assertions.assertDoesNotThrow(productGroupCommandContextRequestDto::toProductGroupCommandContext);
    }

}