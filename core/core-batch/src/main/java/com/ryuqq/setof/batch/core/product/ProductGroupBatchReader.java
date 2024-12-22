package com.ryuqq.setof.batch.core.product;

import com.ryuqq.setof.domain.core.product.ProductGroupFilter;
import com.ryuqq.setof.domain.core.product.ProductGroupInsertRequest;
import com.ryuqq.setof.domain.core.product.ProductGroupQueryService;
import com.ryuqq.setof.enums.core.ProductStatus;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupBatchReader implements ItemReader<List<ProductGroupInsertRequest>> {

    private final ProductGroupQueryService productGroupQueryService;

    private Long cursorId = null;

    public ProductGroupBatchReader(ProductGroupQueryService productGroupQueryService) {
        this.productGroupQueryService = productGroupQueryService;
    }


    @Override
    public List<ProductGroupInsertRequest> read() {

        ProductGroupFilter productGroupFilter = ProductGroupFilter.of(ProductStatus.WAITING, cursorId, 100);

        List<Long> productGroupIds = productGroupQueryService.fetchIdsByFilter(productGroupFilter);

        if (productGroupIds == null || productGroupIds.isEmpty()) {
            return null;
        }

        cursorId = productGroupIds.getLast();
        return productGroupQueryService.fetchProductGroupInsertRequestsByIds(productGroupIds);
    }

}
