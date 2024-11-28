package com.ryuqq.setof.domain.core.site.external.mapper.buyma;

import com.ryuqq.setof.domain.core.category.MappingCategory;
import com.ryuqq.setof.domain.core.product.ProductGroup;
import com.ryuqq.setof.domain.core.product.ProductGroupContext;
import com.ryuqq.setof.domain.core.site.external.ExternalProductUploadData;
import com.ryuqq.setof.domain.core.site.external.mapper.ExternalMallProductPayload;
import com.ryuqq.setof.domain.core.site.external.mapper.ExternalProductMapper;
import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaProduct;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaProductNameContext;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaVariantContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Component
public class BuyMaProductMapper implements ExternalProductMapper {

    private final BuyMaProductDetailGenerator productDetailGenerator;
    private final BuyMaOptionMapper buyMaOptionMapper;
    private final BuyMaPriceGenerator buyMaPriceGenerator;
    private final BuyMaImageGenerator buyMaImageGenerator;

    public BuyMaProductMapper(BuyMaProductDetailGenerator productDetailGenerator, BuyMaOptionMapper buyMaOptionMapper, BuyMaPriceGenerator buyMaPriceGenerator, BuyMaImageGenerator buyMaImageGenerator) {
        this.productDetailGenerator = productDetailGenerator;
        this.buyMaOptionMapper = buyMaOptionMapper;
        this.buyMaPriceGenerator = buyMaPriceGenerator;
        this.buyMaImageGenerator = buyMaImageGenerator;
    }

    public BuyMaProduct toBuyMaProducts(ExternalProductUploadData externalProductUploadData){
        String externalProductId = externalProductUploadData.externalProductContext().externalProductGroup().externalProductId();
        Integer id = StringUtils.hasText(externalProductId) ? Integer.parseInt(externalProductId) : null;

        ProductGroupContext productGroupContext = externalProductUploadData.productGroupContext();
        ProductGroup productGroup = productGroupContext.getProductGroup();
        BuyMaProductNameContext buyMaProductNameContext = productDetailGenerator.generateBuyMaProductDetail(productGroup);

        BigDecimal finalPrice = buyMaPriceGenerator.calculateFinalPrice(productGroup.price(), BigDecimal.valueOf(907));
        BigDecimal referencePrice = finalPrice.multiply(BigDecimal.valueOf(1.5));

        MappingCategory mappingCategory = externalProductUploadData.mappingCategory();

        BuyMaVariantContext variantContexts = buyMaOptionMapper.getVariants(productGroup.productGroupId(), mappingCategory, productGroupContext.getProducts());

        return new BuyMaProduct(
                productGroup.styleCode(),
                productGroup.setOfProductGroupId(),
                buyMaProductNameContext.productName(),
                buyMaProductNameContext.description(),
                Integer.parseInt(externalProductUploadData.mappingBrand().externalBrandId()),
                externalProductUploadData.mappingBrand().brandName(),
                Integer.parseInt(externalProductUploadData.mappingCategory().externalCategoryId()),
                finalPrice.intValue(),
                referencePrice.intValue(),
                buyMaImageGenerator.getImages(externalProductUploadData.productGroupContext().getProductGroup().images()),
                variantContexts.buyMaVariants(),
                variantContexts.buyMaOptions(),
                id,
                variantContexts.optionComment()
        );
    }

    @Override
    public SiteName getSiteName() {
        return SiteName.BUYMA;
    }

    @Override
    public ExternalMallProductPayload transform() {
        return null;
    }
}
