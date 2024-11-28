package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.core.product.image.ProductGroupImageEntity;
import com.ryuqq.setof.storage.db.core.product.image.ProductGroupImagePersistenceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductGroupImageCommandService {

    private final ProductGroupImagePersistenceRepository productGroupImagePersistenceRepository;

    public ProductGroupImageCommandService(ProductGroupImagePersistenceRepository productGroupImagePersistenceRepository) {
        this.productGroupImagePersistenceRepository = productGroupImagePersistenceRepository;
    }

    public void inserts(long productGroupId, List<ProductGroupImageCommand> productGroupImageCommands) {
        List<ProductGroupImageEntity> productGroupImageEntities = productGroupImageCommands.stream()
                .map(productGroupImageCommand -> productGroupImageCommand.toEntity(productGroupId))
                .toList();

        productGroupImagePersistenceRepository.saveAllProductGroupImage(productGroupImageEntities);
    }

    public void updateAndInserts(long productGroupId, List<ProductGroupImageCommand> productGroupImageCommands) {
        List<ProductGroupImageEntity> toInsert = new ArrayList<>();
        List<ProductGroupImageEntity> toUpdate = new ArrayList<>();

        for(ProductGroupImageCommand command : productGroupImageCommands) {
            if(command.id() != null){
                toUpdate.add(command.toEntity(productGroupId));
            }else{
                toInsert.add(command.toEntity(productGroupId));
            }
        }

        if(!toInsert.isEmpty()){
            productGroupImagePersistenceRepository.saveAllProductGroupImage(toInsert);
        }

        if(!toUpdate.isEmpty()){
            productGroupImagePersistenceRepository.updateAllProductGroupImage(toUpdate);
        }
    }

}
