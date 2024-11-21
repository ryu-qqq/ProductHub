package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.domain.core.brand.BrandFinder;
import com.ryuqq.setof.domain.core.brand.BrandQueryService;
import com.ryuqq.setof.domain.core.category.CategoryFinder;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupEntity;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupPersistenceService;
import org.springframework.stereotype.Service;

@Service
public class ProductGroupCommandService {

    private final ProductGroupPersistenceService productGroupPersistenceService;
    private final BrandQueryService brandQueryService;
    private final CategoryFinder categoryFinder;

    public ProductGroupCommandService(ProductGroupPersistenceService productGroupPersistenceService, BrandFinder brandFinder, CategoryFinder categoryFinder) {
        this.productGroupPersistenceService = productGroupPersistenceService;
        this.brandQueryService = brandFinder;
        this.categoryFinder = categoryFinder;
    }

    public long insert(ProductGroupCommand productGroupCommand) {
        validate(productGroupCommand.brandId(), productGroupCommand.categoryId());
        return productGroupPersistenceService.insert(productGroupCommand.toEntity());
    }

    public void update(long productGroupId, ProductGroupCommand productGroupCommand){
        validate(productGroupCommand.brandId(), productGroupCommand.categoryId());
        productGroupPersistenceService.update(productGroupCommand.toEntity(productGroupId));
    }

    private void validate(long brandId, long categoryId) {
        boolean brandExist = brandQueryService.brandExist(brandId);
        if(!brandExist) throw new IllegalArgumentException("Brand does not exist");
        boolean categoryExist = categoryFinder.categoryExist(categoryId);
        if(!categoryExist) throw new IllegalArgumentException("Category does not exist");
    }


}
