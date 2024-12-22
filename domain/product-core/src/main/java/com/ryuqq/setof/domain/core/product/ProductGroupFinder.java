package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.domain.core.exception.NotFoundException;
import com.ryuqq.setof.db.core.product.group.ProductGroupQueryRepository;
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

    @Override
    public List<Long> fetchIdsByFilter(ProductGroupFilter productGroupFilter){
        return productGroupQueryRepository.fetchIdsByFilter(productGroupFilter.toStorageFilterDto());
    }

    @Override
    public ProductGroup fetchById(long productGroupId) {
        return productGroupQueryRepository.fetchById(productGroupId)
                .map(productGroupMapper::toDomain)
                .orElseThrow(() ->
                        new NotFoundException(String.format(PRODUCT_GROUP_NOT_FOUND_ERROR_MSG, productGroupId)));
    }

    @Override
    public List<ProductGroup> fetchProductGroupsByFilter(ProductGroupFilter productGroupFilter){
        List<Long> productGroupIds = productGroupQueryRepository.fetchIdsByFilter(productGroupFilter.toStorageFilterDto());
        return productGroupQueryRepository.fetchByIds(productGroupIds)
                .stream()
                .map(productGroupMapper::toDomain)
                .toList();
    }

    @Override
    public long countByFilter(ProductGroupFilter productGroupFilter){
        return productGroupQueryRepository.countByFilter(productGroupFilter.toStorageFilterDto());
    }


    @Override
    public List<ProductGroupInsertRequest> fetchProductGroupInsertRequestsByIds(List<Long> productGroupIds){
        return productGroupQueryRepository.fetchInsertRequestDtoByIds(productGroupIds).stream()
                .map(productGroupMapper::toProductGroupInsertRequest)
                .toList();
    }


}
