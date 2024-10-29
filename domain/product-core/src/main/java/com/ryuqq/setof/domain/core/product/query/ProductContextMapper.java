package com.ryuqq.setof.domain.core.product.query;

import com.ryuqq.setof.storage.db.core.product.dto.ProductContextDto;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ProductContextMapper {

    public Set<ProductContext> toProductContexts(List<ProductContextDto> products) {
        Map<Long, Set<OptionContext>> groupedOptions = getGroupedOptions(products);
        Map<Long, ProductContextDto> productMap = products.stream()
                .collect(Collectors.toMap(ProductContextDto::getProductId, Function.identity(),
                        (existing, replacement) -> existing));

        return setOption(groupedOptions, productMap);
    }

    private Map<Long, Set<OptionContext>> getGroupedOptions(List<ProductContextDto> products) {
        Map<Long, Set<OptionContext>> groupedOptions = new HashMap<>();
        for (ProductContextDto p : products) {
            groupedOptions
                    .computeIfAbsent(p.getProductId(), k -> new HashSet<>())
                    .add(new OptionContext(p.getOptionGroupId(), p.getOptionDetailId(), p.getOptionName(), p.getOptionValue()));
        }
        return groupedOptions;
    }


    private Set<ProductContext> setOption(Map<Long, Set<OptionContext>> groupedOptions, Map<Long, ProductContextDto> productMap) {
        List<ProductContext> responses = groupedOptions.entrySet().stream().map(entry -> {

                    Long productId = entry.getKey();
                    Set<OptionContext> options = entry.getValue();

                    Set<OptionContext> filteredOptions = options.stream()
                            .filter(option -> option.optionGroupId() != null && option.optionGroupId() != 0)
                            .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(OptionContext::optionGroupId))));

                    if (!filteredOptions.isEmpty()) {
                        filteredOptions.addAll(options);
                    }

                    ProductContextDto productContextDto = productMap.get(productId);
                    if (productContextDto != null) {
                        return new ProductContext(
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
                .sorted(Comparator.comparingLong(ProductContext::productId))
                .toList();

        return new LinkedHashSet<>(responses);
    }

    private String getOptionName(Set<OptionContext> options) {
        return options.stream()
                .sorted(Comparator.comparing(OptionContext::optionGroupId))
                .map(OptionContext::optionValue)
                .collect(Collectors.joining(" "));
    }
}
