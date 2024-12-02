package com.ryuqq.setof.support.external.core.buyma;

import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.*;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaCategoryAndBrandContext;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaPriceContext;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaProductDetailContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class BuyMaProductMapper implements ExternalMallContextMapper {

    private final BuyMaProductNameContextGenerator buyMaProductNameContextGenerator;
    private final BuyMaOptionMapper buyMaOptionMapper;
    private final BuyMaPriceGenerator buyMaPriceGenerator;
    private final BuyMaImageGenerator buyMaImageGenerator;

    public BuyMaProductMapper(BuyMaProductNameContextGenerator buyMaProductNameContextGenerator, BuyMaOptionMapper buyMaOptionMapper, BuyMaPriceGenerator buyMaPriceGenerator, BuyMaImageGenerator buyMaImageGenerator) {
        this.buyMaProductNameContextGenerator = buyMaProductNameContextGenerator;
        this.buyMaOptionMapper = buyMaOptionMapper;
        this.buyMaPriceGenerator = buyMaPriceGenerator;
        this.buyMaImageGenerator = buyMaImageGenerator;
    }

    @Override
    public SiteName getSiteName() {
        return SiteName.BUYMA;
    }

    @Override
    public BuyMaProductDetailContext generateProductDetailContext(ExternalMallPreProductContext externalMallPreProductContext) {
        ExternalMallNameContext externalMallNameContext = generateNameContext(externalMallPreProductContext);
        return new BuyMaProductDetailContext(
                externalMallPreProductContext.getExternalProductId(),
                externalMallPreProductContext.getStyleCode(),
                externalMallPreProductContext.getProductGroupId(),
                externalMallNameContext);
    }

    private ExternalMallNameContext generateNameContext(ExternalMallPreProductContext externalMallPreProductContext) {
        String brandName =  externalMallPreProductContext.brand().brandName();
        ExternalSyncProductGroup productGroup = externalMallPreProductContext.productGroup();
        return buyMaProductNameContextGenerator.generateBuyMaProductDetail(brandName, productGroup);
    }

    @Override
    public ExternalMallPriceContext generatePriceHolder(ExternalMallPreProductContext externalMallPreProductContext) {
        ExternalSyncProductGroup productGroup = externalMallPreProductContext.productGroup();
        BigDecimal finalPrice = buyMaPriceGenerator.calculateFinalPrice(productGroup.currentPrice(), BigDecimal.valueOf(907));
        BigDecimal referencePrice = finalPrice.multiply(BigDecimal.valueOf(1.5));
        return new BuyMaPriceContext(referencePrice, finalPrice, Origin.JP);
    }

    @Override
    public ExternalMallOptionContext generateOptionContext(ExternalMallPreProductContext externalMallPreProductContext) {
        ExternalSyncProductGroup productGroup = externalMallPreProductContext.productGroup();
        List<ExternalSyncProduct> products = externalMallPreProductContext.products();
        return buyMaOptionMapper.getVariants(
                productGroup.productGroupId(),
                externalMallPreProductContext.externalCategoryOptions(),
                externalMallPreProductContext.gptOptionsResult(),
                products
        );
    }

    @Override
    public ExternalMallImageGroupContext generateImageGroupContext(ExternalMallPreProductContext externalMallPreProductContext) {
        return buyMaImageGenerator.getImageGroupContext(externalMallPreProductContext.productGroup().images());
    }


    @Override
    public ExternalMallCategoryAndBrandContext generateCategoryAndBrand(ExternalMallPreProductContext externalMallPreProductContext) {
        String categoryId = externalMallPreProductContext.category().externalCategoryId();
        ExternalSyncBrand brand = externalMallPreProductContext.brand();

        if(categoryId ==null || categoryId.isBlank() || brand == null) {
            throw new IllegalArgumentException("Mapping Brand or Category Id is null");
        }

        return new BuyMaCategoryAndBrandContext(categoryId, brand.externalBrandId(), brand.brandName());
    }

}
