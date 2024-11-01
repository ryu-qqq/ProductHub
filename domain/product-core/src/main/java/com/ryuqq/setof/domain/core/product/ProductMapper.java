package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.core.product.dto.ProductContextDto;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public Set<Product> toProductContexts(List<ProductContextDto> products) {
        Map<Long, Set<Option>> groupedOptions = getGroupedOptions(products);
        Map<Long, ProductContextDto> productMap = products.stream()
                .collect(Collectors.toMap(ProductContextDto::getProductId, Function.identity(),
                        (existing, replacement) -> existing));

        return setOption(groupedOptions, productMap);
    }

    private Map<Long, Set<Option>> getGroupedOptions(List<ProductContextDto> products) {
        Map<Long, Set<Option>> groupedOptions = new HashMap<>();
        for (ProductContextDto p : products) {
            groupedOptions
                    .computeIfAbsent(p.getProductId(), k -> new HashSet<>())
                    .add(new Option(p.getProductId(), p.getOptionGroupId(), p.getOptionDetailId(), p.getOptionName(), p.getOptionValue()));
        }
        return groupedOptions;
    }


    private Set<Product> setOption(Map<Long, Set<Option>> groupedOptions, Map<Long, ProductContextDto> productMap) {
        List<Product> responses = groupedOptions.entrySet().stream().map(entry -> {

                    Long productId = entry.getKey();
                    Set<Option> options = entry.getValue();

                    Set<Option> filteredOptions = options.stream()
                            .filter(option -> option.getOptionGroupId() != null && option.getOptionGroupId() != 0)
                            .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Option::getOptionGroupId))));

                    if (!filteredOptions.isEmpty()) {
                        filteredOptions.addAll(options);
                    }

                    ProductContextDto productContextDto = productMap.get(productId);
                    if (productContextDto != null) {
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
                    return null;
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingLong(Product::getProductId))
                .toList();

        return new LinkedHashSet<>(responses);
    }

    private String getOptionName(Set<Option> options) {
        return options.stream()
                .sorted(Comparator.comparing(Option::getOptionGroupId))
                .map(Option::getOptionValue)
                .collect(Collectors.joining(" "));
    }
}
