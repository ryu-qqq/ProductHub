package com.ryuqq.setof.support.external.core.buyma;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.*;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaProduct;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaProductContext;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaProductNameContext;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaVariantContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

@Component
public class BuyMaProductMapper implements ExternalSyncProductMapper {

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

    @Override
    public SiteName getSiteName() {
        return SiteName.BUYMA;
    }

    @Override
    public ExternalMallRegistrationRequest transform(ExternalMallProductContext externalMallProductContext) {
        long productGroupId = externalMallProductContext.productGroup().productGroupId();
        long siteId = externalMallProductContext.siteId();
        return new ExternalMallRegistrationRequest(productGroupId, siteId, toBuyMaProduct(externalMallProductContext));
    }

    public BuyMaProductContext toBuyMaProduct(ExternalMallProductContext externalMallProductContext){

        String externalProductId = externalMallProductContext.productGroup().externalProductId();
        Integer id = StringUtils.hasText(externalProductId) ? Integer.parseInt(externalProductId) : null;

        ExternalMallProductGroup productGroup = externalMallProductContext.productGroup();
        List<ExternalMallProduct> products = externalMallProductContext.products();

        ExternalMallCategory category = externalMallProductContext.category();
        ExternalMallBrand brand = externalMallProductContext.brand();

        BuyMaProductNameContext buyMaProductNameContext = productDetailGenerator.generateBuyMaProductDetail(brand.brandName(), productGroup);

        BigDecimal finalPrice = buyMaPriceGenerator.calculateFinalPrice(productGroup.currentPrice(), BigDecimal.valueOf(907));
        BigDecimal referencePrice = finalPrice.multiply(BigDecimal.valueOf(1.5));



        BuyMaVariantContext variantContexts = buyMaOptionMapper.getVariants(productGroup.productGroupId(), externalMallProductContext.externalCategoryOptions(), externalMallProductContext.gptOptionsResult(), products);

        BuyMaProduct buyMaProduct = new BuyMaProduct(
                productGroup.styleCode(),
                productGroup.setOfProductGroupId(),
                buyMaProductNameContext.productName(),
                buyMaProductNameContext.description(),
                Integer.parseInt(brand.externalBrandId()),
                brand.brandName(),
                Integer.parseInt(category.externalCategoryId()),
                finalPrice.intValue(),
                referencePrice.intValue(),
                buyMaImageGenerator.getImages(productGroup.images()),
                variantContexts.buyMaVariants(),
                variantContexts.buyMaOptions(),
                id,
                variantContexts.optionComment()
        );

        return new BuyMaProductContext(buyMaProduct);
    }


}
