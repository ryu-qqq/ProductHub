package com.ryuqq.setof.domain.core.product.query;

import com.ryuqq.setof.domain.core.category.Category;
import com.ryuqq.setof.domain.core.category.CategoryDomainQueryService;
import com.ryuqq.setof.domain.core.generic.Slice;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductGroupDomainQueryFacade {

    private final CategoryDomainQueryService categoryDomainQueryService;
    private final ProductGroupFinder productGroupFinder;
    private final ProductFinder productFinder;
    private final ProductGroupContextSliceMapper productGroupContextSliceMapper;

    public ProductGroupDomainQueryFacade(
            CategoryDomainQueryService categoryDomainQueryService,
            ProductGroupFinder productGroupFinder,
            ProductFinder productFinder,
            ProductGroupContextSliceMapper productGroupContextSliceMapper) {
        this.categoryDomainQueryService = categoryDomainQueryService;
        this.productGroupFinder = productGroupFinder;
        this.productFinder = productFinder;
        this.productGroupContextSliceMapper = productGroupContextSliceMapper;
    }

    public Slice<ProductGroupContext> getProductGroupContexts(ProductGroupFilter productGroupFilter) {
        List<Long> categoryIds = getChildrenCategoryIds(productGroupFilter.categoryId());
        List<ProductGroupContext> productGroupContexts = productGroupFinder.getProductGroupContexts(productGroupFilter, categoryIds);
        long productGroupCount = productGroupFinder.findProductGroupCount(productGroupFilter, categoryIds);

        List<Long> productGroupIds = getProductGroupIds(productGroupContexts);
        Set<ProductContext> productContexts = productFinder.getProductGroupContexts(productGroupIds);

        setProductContexts(productGroupContexts, productContexts);

        Set<Long> categoryIdSet = extractCategoryIdsFromProductGroups(productGroupContexts);
        setCategories(productGroupContexts, categoryIdSet);

        return productGroupContextSliceMapper.toSlice(productGroupContexts, productGroupFilter.pageSize(), productGroupCount);
    }

    private void setProductContexts(List<ProductGroupContext> productGroupContexts, Set<ProductContext> productContexts) {
        Map<Long, Set<ProductContext>> productGroupIdMap = productContexts.stream()
                .collect(Collectors.groupingBy(ProductContext::productGroupId, Collectors.toCollection(LinkedHashSet::new)));

        productGroupContexts.forEach(productGroupContext -> {
            Set<ProductContext> value = productGroupIdMap.getOrDefault(
                                productGroupContext.getProductGroup().getProductGroupId(),
                                Collections.emptySet());

            productGroupContext.setProduct(value);
        });
    }

    private List<Long> getProductGroupIds(List<ProductGroupContext> productGroupContexts) {
        return productGroupContexts.stream()
                .map(productGroupContext -> productGroupContext.getProductGroup().getProductGroupId())
                .collect(Collectors.toList());
    }

    private List<Long> getChildrenCategoryIds(Long categoryId) {
        return Optional.ofNullable(categoryId)
                .map(id -> categoryDomainQueryService.getChildCategories(id).stream()
                        .map(Category::id)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    private void setCategories(List<ProductGroupContext> productGroupContexts, Set<Long> categoryIds) {
        Map<Long, Category> categoryMap = createCategoryMap(categoryDomainQueryService.getCategories(new ArrayList<>(categoryIds)));
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

    private Map<Long, Category> createCategoryMap(List<Category> categories) {
        return categories.stream().collect(Collectors.toMap(Category::id, category -> category));
    }

    private List<Category> getCategories(ProductGroupContext context, Map<Long, Category> categoryMap) {
        return Arrays.stream(context.getProductGroup().getCategories().getFirst().path().split(","))
                .map(Long::parseLong)
                .map(categoryMap::get)
                .sorted(Comparator.comparing(Category::id))
                .collect(Collectors.toList());
    }

}