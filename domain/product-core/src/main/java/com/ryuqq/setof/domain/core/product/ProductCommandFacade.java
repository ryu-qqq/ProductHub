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
        Map<Long, List<OptionCommand>> optionMap = createOptionMap(productGroupId, productCommands);

        if (!optionMap.isEmpty()) {
            productOptionCommandService.processOptionCommands(optionMap);
        }
    }

    public void update(long productGroupId, Set<Product> existingProducts, List<ProductCommand> productCommands) {
        ProductUpdater productUpdater = new ProductUpdater(existingProducts, productCommands, productGroupId);

        Map<Long, List<OptionCommand>> optionMap = createOptionMap(productGroupId, productUpdater.getOptionUpdaters());
        if (!optionMap.isEmpty()) {
            productOptionCommandService.processOptionCommands(optionMap);
        }

        List<Long> toDeleteIds = productUpdater.getProductsToUpdate().stream()
                .filter(Product::isDeleteYn)
                .map(Product::getProductId)
                .toList();

        List<ProductEntity> productEntities = productUpdater.getProductsToUpdate().stream()
                .map(Product::toEntity)
                .toList();

        productPersistenceService.updateAll(productEntities);
        productOptionCommandService.deleteOptions(toDeleteIds);
    }

    private Map<Long, List<OptionCommand>> createOptionMap(long productGroupId, List<ProductCommand> productCommands) {
        Map<Long, List<OptionCommand>> optionMap = new LinkedHashMap<>();
        productCommands.forEach(productCommand -> {
            long productId = productPersistenceService.insert(productCommand.toEntity(productGroupId));
            if (!productCommand.options().isEmpty()) {
                optionMap.put(productId, productCommand.options());
            }
        });
        return optionMap;
    }


}
