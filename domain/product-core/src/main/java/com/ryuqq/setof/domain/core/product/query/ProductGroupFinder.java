package com.ryuqq.setof.domain.core.product.query;

import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupContextDto;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupFinder {

    private final ProductGroupContextMapper productGroupContextMapper;
    private final ProductGroupQueryRepository productGroupQueryRepository;

    public ProductGroupFinder(ProductGroupContextMapper productGroupContextMapper, ProductGroupQueryRepository productGroupQueryRepository) {
        this.productGroupContextMapper = productGroupContextMapper;
        this.productGroupQueryRepository = productGroupQueryRepository;
    }

    public List<ProductGroupContext> getProductGroupContexts(ProductGroupFilter productGroupFilter, List<Long> categoryIds){
        List<ProductGroupContextDto> productGroupContextDtos = productGroupQueryRepository.fetchProductGroupContexts(productGroupFilter.toProductGroupStorageFilterDto(categoryIds));
        return productGroupContextDtos.stream()
                .map(productGroupContextMapper::toProductGroupContext)
                .toList();
    }

    public long findProductGroupCount(ProductGroupFilter productGroupFilter, List<Long> categoryIds){
        return productGroupQueryRepository.fetchProductGroupCount(productGroupFilter.toProductGroupStorageFilterDto(categoryIds));
    }

}
