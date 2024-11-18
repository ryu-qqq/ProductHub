package com.ryuqq.setof.producthub.core.api.controller.v1.product.service;

import com.ryuqq.setof.domain.core.category.CategoryQueryService;
import com.ryuqq.setof.domain.core.category.CategoryRecord;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.product.ProductGroupContext;
import com.ryuqq.setof.domain.core.product.ProductGroupFilter;
import com.ryuqq.setof.domain.core.product.ProductGroupQueryService;
import com.ryuqq.setof.producthub.core.api.controller.v1.product.mapper.ProductGroupContextSliceMapper;
import com.ryuqq.setof.producthub.core.api.controller.v1.product.response.ProductGroupContextResponse;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ProductGroupQueryFacade {

    private final CategoryQueryService categoryQueryService;
    private final ProductGroupQueryService productGroupQueryService;
    private final ProductGroupContextSliceMapper productGroupContextSliceMapper;

    public ProductGroupQueryFacade(CategoryQueryService categoryQueryService, ProductGroupQueryService productGroupQueryService, ProductGroupContextSliceMapper productGroupContextSliceMapper) {
        this.categoryQueryService = categoryQueryService;
        this.productGroupQueryService = productGroupQueryService;
        this.productGroupContextSliceMapper = productGroupContextSliceMapper;
    }

    public Slice<ProductGroupContextResponse> getProductGroupContexts(ProductGroupFilter productGroupFilter){
        List<Long> categoryIds = getChildrenCategoryIds(productGroupFilter.categoryId());
        long productGroupCount = productGroupQueryService.findProductGroupCount(productGroupFilter, categoryIds);

        if(productGroupCount == 0){
            return productGroupContextSliceMapper.toSlice(List.of(), productGroupFilter.pageSize(), productGroupCount);
        }

        List<ProductGroupContext> productGroupContexts = productGroupQueryService.findProductGroupContexts(productGroupFilter, categoryIds);

        Set<Long> categoryIdSet = extractCategoryIdsFromProductGroups(productGroupContexts);
        setCategories(productGroupContexts, categoryIdSet);

        List<ProductGroupContextResponse> productGroupContextResponses = productGroupContexts.stream().map(ProductGroupContextResponse::of).toList();
        return productGroupContextSliceMapper.toSlice(productGroupContextResponses, productGroupFilter.pageSize(), productGroupCount);
    }

    private List<Long> getChildrenCategoryIds(Long categoryId) {
        return Optional.ofNullable(categoryId)
                .map(id -> categoryQueryService.findChildCategories(id).stream()
                        .map(CategoryRecord::id)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }



    private void setCategories(List<ProductGroupContext> productGroupContexts, Set<Long> categoryIds) {
        Map<Long, CategoryRecord> categoryMap = createCategoryMap(categoryQueryService.findCategories(new ArrayList<>(categoryIds)));
        productGroupContexts.forEach(context -> context.getProductGroup().setCategories(getCategories(context, categoryMap)));
    }

    private Set<Long> extractCategoryIdsFromProductGroups(List<ProductGroupContext> productGroupContexts) {
        return productGroupContexts.stream()
                .map(ProductGroupContext::getProductGroup)
                .map(p -> p.getCategories().getFirst().path())
                .flatMap(path -> Arrays.stream(path.split(",")))
                .map(Long::parseLong)
                .collect(Collectors.toSet());
    }

    private Map<Long, CategoryRecord> createCategoryMap(List<CategoryRecord> categories) {
        return categories.stream().collect(Collectors.toMap(CategoryRecord::id, category -> category));
    }

    private List<CategoryRecord> getCategories(ProductGroupContext context, Map<Long, CategoryRecord> categoryMap) {
        return Arrays.stream(context.getProductGroup().getCategories().getFirst().path().split(","))
                .map(Long::parseLong)
                .map(categoryMap::get)
                .sorted(Comparator.comparing(CategoryRecord::id))
                .collect(Collectors.toList());
    }


}
