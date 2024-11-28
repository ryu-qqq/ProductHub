package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.domain.core.exception.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ryuqq.setof.domain.core.product.ProductErrorConstants.PRODUCT_GROUP_NOT_FOUND_ERROR_MSG;

@Component
public class ProductGroupContextFinder implements ProductGroupContextQueryService {

    private final ProductGroupFinder productGroupFinder;
    private final ProductGroupContextAssembler assembler;
    private final ProductGroupContextMapper productGroupContextMapper;

    public ProductGroupContextFinder(ProductGroupFinder productGroupFinder, ProductGroupContextAssembler assembler, ProductGroupContextMapper productGroupContextMapper) {
        this.productGroupFinder = productGroupFinder;
        this.assembler = assembler;
        this.productGroupContextMapper = productGroupContextMapper;
    }

    @Override
    public List<ProductGroupContext> fetchProductGroupContextsByFilter(ProductGroupFilter productGroupFilter){
        List<ProductGroup> productGroups = productGroupFinder.fetchProductGroupsByFilter(productGroupFilter);
        if(productGroups.isEmpty()) return List.of();
        ProductGroupContextAggregate productGroupContextAggregate = assembler.assembleContexts(productGroups);

        return productGroupContextMapper.toDomains(productGroupContextAggregate);
    }

    @Override
    public long countProductContextByFilter(ProductGroupFilter productGroupFilter){
        return productGroupFinder.countByFilter(productGroupFilter);
    }

    @Override
    public ProductGroupContext fetchProductGroupContextById(long productGroupId) {
        ProductGroup productGroup = productGroupFinder.fetchById(productGroupId);
        ProductGroupContextAggregate productGroupContextAggregate = assembler.assembleContexts(List.of(productGroup));
        return productGroupContextMapper.toDomains(productGroupContextAggregate).stream()
                .findFirst()
                .orElseThrow(() ->
                        new NotFoundException(String.format(PRODUCT_GROUP_NOT_FOUND_ERROR_MSG, productGroupId)));
    }


}
