package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.core.product.option.ProductEntity;
import com.ryuqq.setof.storage.db.core.product.option.ProductPersistenceService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ProductCommandFacade {

    private final ProductPersistenceService productPersistenceService;
    private final ProductOptionCommandService productOptionCommandService;

    public ProductCommandFacade(ProductPersistenceService productPersistenceService, ProductOptionCommandService productOptionCommandService) {
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

    public void update(long productGroupId, Set<Product> existingProducts, List<ProductCommand> productCommands) {
        ProductUpdater productUpdater = new ProductUpdater(existingProducts, productCommands, productGroupId);

        List<ProductCommand> optionUpdaters = productUpdater.getOptionUpdaters();
        Map<Long, List<OptionCommand>> optionMap = new LinkedHashMap<>();

        optionUpdaters.forEach(productCommand -> {
            long productId = productPersistenceService.insert(productCommand.toEntity(productGroupId));
            optionMap.put(productId, productCommand.options());
            productOptionCommandService.inserts(optionMap);
        });

        List<Product> productsToUpdate = productUpdater.getProductsToUpdate();

        List<Product> toDeleteList = productsToUpdate.stream()
                .filter(Product::isDeleteYn)
                .toList();

        productOptionCommandService.deletes(toDeleteList);
    }

}
