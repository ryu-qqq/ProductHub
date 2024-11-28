package com.ryuqq.setof.domain.core.product;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ProductGroupConfigContextMapper {

    public List<ProductGroupConfigContext> toDomain(ProductGroupConfigContextAggregate aggregate) {

        Map<Long, List<ProductGroupNameConfig>> productNameConfigMap = aggregate.productGroupNameConfigs().stream()
                .collect(Collectors.groupingBy(ProductGroupNameConfig::productGroupId));

        return aggregate.productGroupConfigs().stream()
                .map(groupConfig -> new ProductGroupConfigContext(
                        groupConfig,
                        productNameConfigMap.get(groupConfig.getProductGroupId()),
                        List.of()
                ))
                .toList();

    }
}
