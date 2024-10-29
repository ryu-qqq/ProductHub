package com.ryuqq.setof.domain.core.product.command;

import com.ryuqq.setof.storage.db.core.product.option.ProductEntity;
import com.ryuqq.setof.storage.db.core.product.option.ProductPersistenceService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductInsertFacade {

    private final ProductPersistenceService productPersistenceService;
    private final ProductOptionCommandService productOptionCommandService;

    public ProductInsertFacade(ProductPersistenceService productPersistenceService, ProductOptionCommandService productOptionCommandService) {
        this.productPersistenceService = productPersistenceService;
        this.productOptionCommandService = productOptionCommandService;
    }


    public void insert(long productGroupId, List<ProductCommand> productCommands) {
        Map<Long, List<OptionCommand>> optionMap = new LinkedHashMap<>();

        productCommands.forEach(productCommand -> {
            ProductEntity productEntity = productCommand.toEntity(productGroupId);
            long productId = productPersistenceService.insert(productEntity);

            if (!productCommand.options().isEmpty()) {
                optionMap.put(productId, productCommand.options());
            }
        });

        if (!optionMap.isEmpty()) {
            productOptionCommandService.inserts(optionMap);
        }

    }

}
