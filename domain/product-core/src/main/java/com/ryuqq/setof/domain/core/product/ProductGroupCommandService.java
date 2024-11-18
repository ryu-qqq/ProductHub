package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.domain.core.brand.BrandFinder;
import com.ryuqq.setof.domain.core.category.CategoryFinder;
import com.ryuqq.setof.enums.core.ProductStatus;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupPersistenceService;
import com.ryuqq.setof.storage.db.core.product.notice.ProductNoticePersistenceService;
import org.springframework.stereotype.Service;

@Service
public class ProductGroupCommandService {

    private final ProductGroupPersistenceService productGroupPersistenceService;
    private final BrandFinder brandFinder;
    private final CategoryFinder categoryFinder;

    public ProductGroupCommandService(ProductGroupPersistenceService productGroupPersistenceService, ProductNoticePersistenceService productNoticePersistenceService, BrandFinder brandFinder, CategoryFinder categoryFinder) {
        this.productGroupPersistenceService = productGroupPersistenceService;
        this.brandFinder = brandFinder;
        this.categoryFinder = categoryFinder;
    }

    public long insert(ProductGroupCommand productGroupCommand) {
        validate(productGroupCommand.brandId(), productGroupCommand.categoryId());
        return productGroupPersistenceService.insert(productGroupCommand.toEntity());
    }

    public void update(ProductGroup productGroup, ProductGroupCommand productGroupCommand) {
        validate(productGroupCommand.brandId(), productGroupCommand.categoryId());
        ProductGroupUpdater updater = new ProductGroupUpdater(productGroup);

        if (updater.hasUpdates(productGroupCommand)) {
            productGroupPersistenceService.update(productGroupCommand.toEntity(productGroup.getProductGroupId()));
        }
    }

    public void updateProductStatus(long productGroupId, ProductStatus productStatus){
        productGroupPersistenceService.updateProductStatus(productGroupId, productStatus);
    }


    private void validate(long brandId, long categoryId) {
        boolean brandExist = brandFinder.brandExist(brandId);

        if(!brandExist) throw new IllegalArgumentException("Brand does not exist");

        boolean categoryExist = categoryFinder.categoryExist(categoryId);

        if(!categoryExist) throw new IllegalArgumentException("Category does not exist");
    }

}
