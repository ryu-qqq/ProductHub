package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.core.product.dto.ProductContextDto;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public List<Product> toDomains(List<ProductContextDto> products) {
        Map<Long, Set<Option>> groupedOptions = getGroupedOptions(products);
        Map<Long, ProductContextDto> productMap =getProductMap(products);

        return productMap.values().stream()
                .map(dto -> mapToProduct(dto, groupedOptions))
                .sorted(Comparator.comparing(Product::getProductId)) // productId로 정렬
                .toList();
    }

    private Map<Long, Set<Option>> getGroupedOptions(List<ProductContextDto> products) {
        return products.stream()
                .collect(Collectors.groupingBy(
                        ProductContextDto::getProductId,
                        Collectors.mapping(
                                p -> new Option(
                                        p.getProductId(),
                                        p.getOptionGroupId(),
                                        p.getOptionDetailId(),
                                        p.getOptionName(),
                                        p.getOptionValue()
                                ),
                                Collectors.toSet()
                        )
                ));
    }

    private Map<Long, ProductContextDto> getProductMap(List<ProductContextDto> products) {
        return products.stream()
                .collect(Collectors.toMap(ProductContextDto::getProductId, Function.identity(),
                        (existing, replacement) -> existing));
    }

    private Product mapToProduct(ProductContextDto productContextDto, Map<Long, Set<Option>> groupedOptions) {
        Long productId = productContextDto.getProductId();
        Set<Option> options = groupedOptions.getOrDefault(productId, Set.of());

        Set<Option> filteredOptions = options.stream()
                .filter(option -> option.getOptionGroupId() != null && option.getOptionGroupId() != 0)
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Option::getOptionGroupId))));

        if (!filteredOptions.isEmpty()) {
            filteredOptions.addAll(options);
        }

        return new Product(
                productContextDto.getProductGroupId(),
                productContextDto.getProductId(),
                productContextDto.getQuantity(),
                productContextDto.isSoldOutYn(),
                productContextDto.isDisplayYn(),
                getOptionName(filteredOptions),
                filteredOptions,
                productContextDto.getAdditionalPrice()
        );
    }

    private String getOptionName(Set<Option> options) {
        return options.stream()
                .sorted(Comparator.comparing(Option::getOptionGroupId))
                .map(Option::getOptionValue)
                .collect(Collectors.joining(" "));
    }
}
