package com.ryuqq.setof.domain.core.product.command;

import com.ryuqq.setof.storage.db.core.product.option.ProductEntity;
import com.ryuqq.setof.storage.db.core.product.option.ProductPersistenceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductCommandService {

    private final ProductPersistenceService productPersistenceService;
    private final ProductOptionCommandService productOptionCommandService;

    public ProductCommandService(ProductPersistenceService productPersistenceService, ProductOptionCommandService productOptionCommandService) {
        this.productPersistenceService = productPersistenceService;
        this.productOptionCommandService = productOptionCommandService;
    }


    public List<Long> insert(long productGroupId, List<ProductCommand> productCommands) {
        Map<Long, List<OptionCommand>> optionMap = new LinkedHashMap<>();
        List<Long> productIds = new ArrayList<>();

        productCommands.forEach(productCommand -> {
            ProductEntity productEntity = productCommand.toEntity(productGroupId);
            long productId = productPersistenceService.insert(productEntity);
            productIds.add(productId);

            if (!productCommand.options().isEmpty()) {
                optionMap.put(productId, productCommand.options());
            }
        });

        if (!optionMap.isEmpty()) {
            productOptionCommandService.inserts(optionMap);
        }

        return productIds;
    }

}
