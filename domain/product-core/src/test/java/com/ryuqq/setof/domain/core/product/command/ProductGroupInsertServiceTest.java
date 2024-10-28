package com.ryuqq.setof.domain.core.product.command;

import com.ryuqq.setof.domain.core.product.query.ProductGroupFinder;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupEntity;
import data.ProductModuleHelper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ProductGroupInsertServiceTest extends ContextTest{

    private final ProductGroupInsertService productGroupInsertService;
    private final ProductGroupFinder productGroupQueryService;

    ProductGroupInsertServiceTest(ProductGroupInsertService productGroupInsertService, ProductGroupFinder productGroupQueryService) {
        this.productGroupInsertService = productGroupInsertService;
        this.productGroupQueryService = productGroupQueryService;
    }

    @Test
    void testInsertRollbackOnException(){
        ProductGroupCommandContext context = ProductModuleHelper.toProductGroupCommandContext();
        assertThrows(RuntimeException.class, () -> {
            productGroupInsertService.insert(context);
        });

        List<ProductGroupEntity> productGroupEntities = productGroupQueryService.getProductGroupEntities();
        assertTrue(productGroupEntities.isEmpty(), "ProductGroup should be rolled back and not exist.");


    }
}