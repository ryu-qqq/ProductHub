package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.core.product.image.ProductGroupImageEntity;
import com.ryuqq.setof.storage.db.core.product.image.ProductGroupImagePersistenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductGroupImageCommandService {

    private final ProductGroupImagePersistenceService productGroupImagePersistenceService;
    private final ProductGroupImageFinder productGroupImageFinder;


    public ProductGroupImageCommandService(ProductGroupImagePersistenceService productGroupImagePersistenceService, ProductGroupImageFinder productGroupImageFinder) {
        this.productGroupImagePersistenceService = productGroupImagePersistenceService;
        this.productGroupImageFinder = productGroupImageFinder;
    }

    public void inserts(long productGroupId, List<ProductGroupImageCommand> productGroupImageCommands) {
        List<ProductGroupImageEntity> productGroupImageEntities = productGroupImageCommands.stream()
                .map(productGroupImageCommand -> productGroupImageCommand.toEntity(productGroupId))
                .toList();

        productGroupImagePersistenceService.insertAll(productGroupImageEntities);
    }

    public void updates(long productGroupId, List<ProductGroupImageCommand> productGroupImageCommands) {
        List<ProductGroupImage> productGroupImages = productGroupImageFinder.getProductGroupImages(productGroupId);
        ProductGroupImageUpdater productGroupImageUpdater = new ProductGroupImageUpdater(productGroupId, productGroupImages, productGroupImageCommands);

        List<ProductGroupImageEntity> toInsert = productGroupImageUpdater.getToInsert();
        List<ProductGroupImageEntity> toUpdate = productGroupImageUpdater.getToUpdate();

        if(!toInsert.isEmpty()){
            productGroupImagePersistenceService.insertAll(toInsert);
        }

        if(!toUpdate.isEmpty()){
            productGroupImagePersistenceService.updateAll(toUpdate);
        }
    }

}
