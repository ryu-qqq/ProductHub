package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.db.core.product.option.ProductEntity;
import com.ryuqq.setof.db.core.product.option.ProductPersistenceRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductCommandService {

    private final ProductPersistenceRepository productPersistenceRepository;
    private final ProductOptionCommandService productOptionCommandService;

    public ProductCommandService(ProductPersistenceRepository productPersistenceRepository, ProductOptionCommandService productOptionCommandService) {
        this.productPersistenceRepository = productPersistenceRepository;
        this.productOptionCommandService = productOptionCommandService;
    }

    public void insert(long productGroupId, List<ProductCommand> productCommands) {
        Map<Long, List<OptionCommand>> optionMap = createOptionMap(productGroupId, productCommands);

        if (!optionMap.isEmpty()) {
            productOptionCommandService.processOptionCommands(optionMap);
        }
    }

    public void update(long productGroupId, List<ProductCommand> productCommands) {
        List<ProductCommand> toInserts = new ArrayList<>();
        List<ProductCommand> toUpdates = new ArrayList<>();

        for(ProductCommand productCommand : productCommands) {
            if(productCommand.id() != null){
                toUpdates.add(productCommand);
            }else{
                toInserts.add(productCommand);
            }
        }



        Map<Long, List<OptionCommand>> optionMap = createOptionMap(productGroupId, toInserts);
        if (!optionMap.isEmpty()) {
            productOptionCommandService.processOptionCommands(optionMap);
        }

        List<Long> toDeleteIds = toUpdates.stream()
                .filter(ProductCommand::deleteYn)
                .map(ProductCommand::id)
                .toList();

        List<ProductEntity> productEntities = toUpdates.stream()
                .map(p -> p.toEntity(productGroupId))
                .toList();

        productPersistenceRepository.updateAllProduct(productEntities);
        productOptionCommandService.deleteOptions(toDeleteIds);
    }


    private Map<Long, List<OptionCommand>> createOptionMap(long productGroupId, List<ProductCommand> productCommands) {
        Map<Long, List<OptionCommand>> optionMap = new LinkedHashMap<>();
        productCommands.forEach(productCommand -> {
            long productId = productPersistenceRepository.insertProduct(productCommand.toEntity(productGroupId));
            if (!productCommand.options().isEmpty()) {
                optionMap.put(productId, productCommand.options());
            }
        });

        return optionMap;
    }


}
