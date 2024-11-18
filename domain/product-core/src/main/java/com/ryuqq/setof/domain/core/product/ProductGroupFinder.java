package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.domain.core.exception.NotFoundException;
import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupContextDto;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupQueryRepository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static com.ryuqq.setof.domain.core.product.ProductErrorConstants.PRODUCT_GROUP_NOT_FOUND_ERROR_MSG;

@Component
public class ProductGroupFinder implements ProductGroupQueryService {

    private final ProductGroupContextMapper productGroupContextMapper;
    private final ProductGroupQueryRepository productGroupQueryRepository;
    private final ProductFinder productFinder;


    public ProductGroupFinder(ProductGroupContextMapper productGroupContextMapper, ProductGroupQueryRepository productGroupQueryRepository, ProductFinder productFinder) {
        this.productGroupContextMapper = productGroupContextMapper;
        this.productGroupQueryRepository = productGroupQueryRepository;
        this.productFinder = productFinder;
    }

    @Override
    public ProductGroupContext findProductGroupContext(long productGroupId) {
        ProductGroupContextDto productGroupContextDto = productGroupQueryRepository.fetchProductGroupContext(productGroupId)
                .orElseThrow(() -> new NotFoundException(PRODUCT_GROUP_NOT_FOUND_ERROR_MSG + productGroupId));

        Set<Product> product = productFinder.findProducts(List.of(productGroupId));
        ProductGroupContext productGroupContext = productGroupContextMapper.toProductGroupContext(productGroupContextDto);
        setProductContexts(List.of(productGroupContext), product);
        return productGroupContext;
    }

    @Override
    public List<ProductGroupContext> findProductGroupContexts(ProductGroupFilter productGroupFilter, List<Long> categoryIds){
        List<ProductGroupContextDto> productGroupContextDtos = productGroupQueryRepository.fetchProductGroupContexts(productGroupFilter.toProductGroupStorageFilterDto(categoryIds));
        List<ProductGroupContext> productGroupContexts = productGroupContextDtos.stream()
                .map(productGroupContextMapper::toProductGroupContext)
                .toList();

        List<Long> productGroupIds = getProductGroupIds(productGroupContexts);
        Set<Product> product = productFinder.findProducts(productGroupIds);
        setProductContexts(productGroupContexts, product);

        return productGroupContexts;
    }

    public long findProductGroupCount(ProductGroupFilter productGroupFilter, List<Long> categoryIds){
        return productGroupQueryRepository.fetchProductGroupCount(productGroupFilter.toProductGroupStorageFilterDto(categoryIds));
    }


    private List<Long> getProductGroupIds(List<ProductGroupContext> productGroupContexts) {
        return productGroupContexts.stream()
                .map(productGroupContext -> productGroupContext.getProductGroup().getProductGroupId())
                .collect(Collectors.toList());
    }

    private void setProductContexts(List<ProductGroupContext> productGroups, Set<Product> products) {
        Map<Long, LinkedHashSet<Product>> productGroupIdMap = products.stream()
                .collect(Collectors.groupingBy(Product::getProductGroupId, Collectors.toCollection(LinkedHashSet::new)));

        productGroups.forEach(productGroupContext -> {
            LinkedHashSet<Product> value = productGroupIdMap.getOrDefault(
                    productGroupContext.getProductGroup().getProductGroupId(),
                    new LinkedHashSet<>());

            productGroupContext.setProducts(value);
        });
    }

}
