package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.core.OptionName;
import com.ryuqq.setof.storage.db.core.product.option.mapping.ProductOptionEntity;
import com.ryuqq.setof.storage.db.core.product.option.mapping.ProductOptionPersistenceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductOptionCommandService {

    private final OptionGroupCommandService optionGroupCommandService;
    private final OptionDetailCommandService optionDetailCommandService;
    private final ProductOptionPersistenceService productOptionPersistenceService;

    public ProductOptionCommandService(OptionGroupCommandService optionGroupCommandService, OptionDetailCommandService optionDetailCommandService, ProductOptionPersistenceService productOptionPersistenceService) {
        this.optionGroupCommandService = optionGroupCommandService;
        this.optionDetailCommandService = optionDetailCommandService;
        this.productOptionPersistenceService = productOptionPersistenceService;
    }

    public void inserts(Map<Long, List<OptionCommand>> productIdOptionMap) {
        Map<OptionName, Long> optionGroupMap = new LinkedHashMap<>();
        Map<String, Long> optionDetailMap = new LinkedHashMap<>();
        List<ProductOptionEntity> productOptionEntities = new ArrayList<>();

        productIdOptionMap.forEach((productId, optionCommands) -> {
            optionCommands.forEach(option -> {
                long optionGroupId = optionGroupMap.computeIfAbsent(option.name(), name ->
                        optionGroupCommandService.insert(option));

                long optionDetailId = optionDetailMap.computeIfAbsent(option.value(), value ->
                        optionDetailCommandService.insert(optionGroupId, option));

                ProductOptionEntity productOptionEntity = new ProductOptionEntity(productId, optionGroupId, optionDetailId);
                productOptionEntities.add(productOptionEntity);
            });
        });

        productOptionPersistenceService.insertAll(productOptionEntities);
    }


    public void deletes(List<Product> products) {

        List<Long> productIds = products.stream()
                .map(Product::getProductId)
                .toList();

        List<Long> optionDetailIds = products.stream()
                .flatMap(product -> product.getOptions().stream())
                .map(Option::getOptionDetailId)
                .toList();

        productOptionPersistenceService.deleteAll(productIds);
        optionDetailCommandService.deleteAll(optionDetailIds);

    }


}
