package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.domain.core.exception.NotFoundException;
import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupDto;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ryuqq.setof.domain.core.product.ProductErrorConstants.PRODUCT_GROUP_NOT_FOUND_ERROR_MSG;

@Component
public class ProductGroupFinder implements ProductGroupQueryService{

    private final ProductGroupMapper productGroupMapper;
    private final ProductGroupQueryRepository productGroupQueryRepository;

    public ProductGroupFinder(ProductGroupMapper productGroupMapper, ProductGroupQueryRepository productGroupQueryRepository) {
        this.productGroupMapper = productGroupMapper;
        this.productGroupQueryRepository = productGroupQueryRepository;
    }

    public List<Long> fetchIdsByFilter(ProductGroupFilter productGroupFilter){
        return productGroupQueryRepository.fetchIdsByFilter(productGroupFilter.toStorageFilterDto());
    }

    public ProductGroup fetchById(long productGroupId) {
        return productGroupQueryRepository.fetchById(productGroupId)
                .map(productGroupMapper::toDomain)
                .orElseThrow(() ->
                        new NotFoundException(String.format(PRODUCT_GROUP_NOT_FOUND_ERROR_MSG, productGroupId)));
    }

    public List<ProductGroup> fetchProductGroupsByFilter(ProductGroupFilter productGroupFilter){
        List<Long> productGroupIds = productGroupQueryRepository.fetchIdsByFilter(productGroupFilter.toStorageFilterDto());
        return productGroupQueryRepository.fetchByIds(productGroupIds)
                .stream()
                .map(productGroupMapper::toDomain)
                .toList();
    }

    public long countByFilter(ProductGroupFilter productGroupFilter){
        return productGroupQueryRepository.countByFilter(productGroupFilter.toStorageFilterDto());
    }

}
