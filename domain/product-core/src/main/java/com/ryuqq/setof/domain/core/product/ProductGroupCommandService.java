package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.domain.core.brand.BrandQueryService;
import com.ryuqq.setof.domain.core.category.CategoryQueryService;
import com.ryuqq.setof.enums.core.ProductStatus;
import com.ryuqq.setof.db.core.product.group.ProductGroupPersistenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductGroupCommandService {

    private final ProductGroupPersistenceRepository productGroupPersistenceRepository;
    private final BrandQueryService brandQueryService;
    private final CategoryQueryService categoryQueryService;

    public ProductGroupCommandService(ProductGroupPersistenceRepository productGroupPersistenceRepository, BrandQueryService brandQueryService, CategoryQueryService categoryQueryService) {
        this.productGroupPersistenceRepository = productGroupPersistenceRepository;
        this.brandQueryService = brandQueryService;
        this.categoryQueryService = categoryQueryService;
    }


    public long insert(ProductGroupCommand productGroupCommand) {
        validate(productGroupCommand.brandId(), productGroupCommand.categoryId());
        return productGroupPersistenceRepository.insert(productGroupCommand.toEntity());
    }

    public void update(long productGroupId, ProductGroupCommand productGroupCommand){
        validate(productGroupCommand.brandId(), productGroupCommand.categoryId());
        productGroupPersistenceRepository.update(productGroupCommand.toEntity(productGroupId));
    }


    public void updateStatus(List<Long> productGroupIds, ProductStatus productStatus){
        productGroupPersistenceRepository.updatesProductStatus(productGroupIds, productStatus);
    }


    private void validate(long brandId, long categoryId) {
        boolean brandExist = brandQueryService.existById(brandId);
        if(!brandExist) throw new IllegalArgumentException("Brand does not exist");

        boolean categoryExist = categoryQueryService.existById(categoryId);
        if(!categoryExist) throw new IllegalArgumentException("Category does not exist");
    }


}
