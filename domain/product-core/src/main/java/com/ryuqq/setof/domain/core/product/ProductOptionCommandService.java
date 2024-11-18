package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.enums.core.OptionName;
import com.ryuqq.setof.storage.db.core.product.option.mapping.ProductOptionEntity;
import com.ryuqq.setof.storage.db.core.product.option.mapping.ProductOptionPersistenceService;
import org.springframework.stereotype.Service;

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

    public void processOptionCommands(Map<Long, List<OptionCommand>> productIdOptionMap) {
        Map<OptionName, Long> optionGroupMap = new LinkedHashMap<>();
        Map<String, Long> optionDetailMap = new LinkedHashMap<>();

        List<ProductOptionEntity> productOptionEntities = productIdOptionMap.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream()
                        .map(option -> createProductOptionEntity(entry.getKey(), option, optionGroupMap, optionDetailMap)))
                .toList();

        productOptionPersistenceService.insertAll(productOptionEntities);
    }

    private ProductOptionEntity createProductOptionEntity(
            Long productId,
            OptionCommand option,
            Map<OptionName, Long> optionGroupMap,
            Map<String, Long> optionDetailMap) {

        long optionGroupId = optionGroupMap.computeIfAbsent(option.name(), name ->
                optionGroupCommandService.insert(option));

        long optionDetailId = optionDetailMap.computeIfAbsent(option.value(), value ->
                optionDetailCommandService.insert(optionGroupId, option));

        return new ProductOptionEntity(productId, optionGroupId, optionDetailId);
    }


    public void deleteOptions(List<Long> productIds) {
        productOptionPersistenceService.deleteAll(productIds);
    }





}
