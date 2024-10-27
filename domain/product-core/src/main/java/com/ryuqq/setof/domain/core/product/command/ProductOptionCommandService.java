package com.ryuqq.setof.domain.core.product.command;

import com.ryuqq.setof.core.OptionName;
import com.ryuqq.setof.storage.db.core.product.option.mapping.ProductOptionEntity;
import com.ryuqq.setof.storage.db.core.product.option.mapping.ProductOptionPersistenceService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
        Map<OptionName, Long> optionGroupMap = new HashMap<>(); // OptionName과 Group ID 매핑
        Map<String, Long> optionDetailMap = new HashMap<>(); // Option value와 Detail ID 매핑

        productIdOptionMap.forEach((productId, optionCommands) -> {
            if (optionCommands.size() == 2) {

                OptionCommand option1 = optionCommands.get(0);
                OptionCommand option2 = optionCommands.get(1);

                long optionGroupId1 = optionGroupMap.computeIfAbsent(option1.name(), name ->
                        optionGroupCommandService.insert(option1));
                long optionGroupId2 = optionGroupMap.computeIfAbsent(option2.name(), name ->
                        optionGroupCommandService.insert(option2));

                long optionDetailId1 = optionDetailMap.computeIfAbsent(option1.value(), value ->
                        optionDetailCommandService.insert(optionGroupId1, option1));
                long optionDetailId2 = optionDetailMap.computeIfAbsent(option2.value(), value ->
                        optionDetailCommandService.insert(optionGroupId2, option2));


                ProductOptionEntity productOptionEntity1 = new ProductOptionEntity(productId, optionGroupId1, optionDetailId1);
                ProductOptionEntity productOptionEntity2 = new ProductOptionEntity(productId, optionGroupId2, optionDetailId2);

                productOptionPersistenceService.insert(productOptionEntity1);
                productOptionPersistenceService.insert(productOptionEntity2);
            } else if (optionCommands.size() == 1) {

                OptionCommand option = optionCommands.getFirst();


                long optionGroupId = optionGroupMap.computeIfAbsent(option.name(), name ->
                        optionGroupCommandService.insert(option));

                long optionDetailId = optionDetailMap.computeIfAbsent(option.value(), value ->
                        optionDetailCommandService.insert(optionGroupId, option));

                ProductOptionEntity productOptionEntity = new ProductOptionEntity(productId, optionGroupId, optionDetailId);
                productOptionPersistenceService.insert(productOptionEntity);
            }
        });
    }

}
