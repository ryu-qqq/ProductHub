package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.enums.core.ProductStatus;
import com.ryuqq.setof.storage.db.index.product.ProductGroupDocumentCommandService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductGroupDocumentCommandFacade {
    private final ProductGroupCommandService productGroupCommandService;
    private final ProductGroupQueryService productGroupQueryService;
    private final ProductGroupDocumentCommandService productGroupDocumentCommandService;

    public ProductGroupDocumentCommandFacade(ProductGroupCommandService productGroupCommandService, ProductGroupQueryService productGroupQueryService, ProductGroupDocumentCommandService productGroupDocumentCommandService) {
        this.productGroupCommandService = productGroupCommandService;
        this.productGroupQueryService = productGroupQueryService;
        this.productGroupDocumentCommandService = productGroupDocumentCommandService;
    }

    @Transactional
    public long insert(ProductGroupCommandContext context) {
        long productGroupId = productGroupCommandService.insert(context.productGroupCommand());
        productGroupDocumentCommandService.insert(context.toDocument(productGroupId));
        return productGroupId;
    }


    @Transactional
    public long update(long productGroupId, ProductGroupCommandContext context) {
        ProductStatus productStatus = productGroupQueryService.findProductStatus(productGroupId);

        return productGroupId;
    }




}
