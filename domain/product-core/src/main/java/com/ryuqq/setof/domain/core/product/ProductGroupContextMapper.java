package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.domain.core.brand.Brand;
import com.ryuqq.setof.domain.core.category.Category;
import com.ryuqq.setof.domain.core.category.CategoryRelation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ProductGroupContextMapper {

    public List<ProductGroupContext> toDomains(ProductGroupContextAggregate aggregate) {
        Map<Long, Brand> brandMap = aggregate.brands().stream()
                .collect(Collectors.toMap(Brand::id, Function.identity(), (v1, v2) -> v2));

        Map<Long, CategoryRelation> categoryMap = aggregate.categories().stream()
                .collect(Collectors.toMap(CategoryRelation::categoryId, Function.identity(), (v1, v2) -> v2));

        Map<Long, List<Product>> productsByGroupId = aggregate.products().stream()
                .collect(Collectors.groupingBy(Product::getProductGroupId));

        Map<Long, ProductGroupConfigContext> configMap = aggregate.configs().stream()
                .collect(Collectors.toMap(ProductGroupConfigContext::getConfigId, Function.identity(), (v1, v2) -> v2));

        return aggregate.productGroups().stream()
                .map(group -> new ProductGroupContext(
                        group,
                        brandMap.get(group.brandId()),
                        categoryMap.get(group.categoryId()).categories(),
                        productsByGroupId.getOrDefault(group.productGroupId(), List.of()),
                        configMap.get(group.configId())
                ))
                .toList();
    }

}
