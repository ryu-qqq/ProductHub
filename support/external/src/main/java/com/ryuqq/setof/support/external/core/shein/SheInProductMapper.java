package com.ryuqq.setof.support.external.core.shein;

import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.*;
import com.ryuqq.setof.support.external.core.shein.domain.SheInCategoryAndBrandContext;
import com.ryuqq.setof.support.external.core.shein.domain.SheInPriceContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SheInProductMapper implements ExternalMallContextMapper {

    private final SheInProductNameContextGenerator sheInProductNameContextGenerator;
    private final SheInOptionMapper sheInOptionMapper;
    private final SheInPriceGenerator sheInPriceGenerator;
    private final SheInImageGenerator sheInImageGenerator;

    public SheInProductMapper(SheInProductNameContextGenerator sheInProductNameContextGenerator, SheInOptionMapper sheInOptionMapper, SheInPriceGenerator sheInPriceGenerator, SheInImageGenerator sheInImageGenerator) {
        this.sheInProductNameContextGenerator = sheInProductNameContextGenerator;
        this.sheInOptionMapper = sheInOptionMapper;
        this.sheInPriceGenerator = sheInPriceGenerator;
        this.sheInImageGenerator = sheInImageGenerator;
    }

    @Override
    public SiteName getSiteName() {
        return SiteName.SHEIN;
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
        return sheInProductNameContextGenerator.generateBuyMaProductDetail(brandName, productGroup);
    }

    @Override
    public ExternalMallPriceContext generatePriceHolder(ExternalMallPreProductContext externalMallPreProductContext) {
        ExternalSyncProductGroup productGroup = externalMallPreProductContext.productGroup();
        BigDecimal finalPrice = sheInPriceGenerator.calculateFinalPrice(productGroup.currentPrice(), BigDecimal.valueOf(1415));
        BigDecimal referencePrice = finalPrice.multiply(BigDecimal.valueOf(1.2));
        return new SheInPriceContext(referencePrice, finalPrice, Origin.US);
    }

    @Override
    public ExternalMallOption generateOptionContext(ExternalMallPreProductContext externalMallPreProductContext) {
        ExternalSyncProductGroup productGroup = externalMallPreProductContext.productGroup();
        List<ExternalSyncProduct> products = externalMallPreProductContext.products();

        return sheInOptionMapper.getAttributes(
                productGroup.setOfProductGroupId(),
                productGroup.styleCode(),
                productGroup.currentPrice(),
                externalMallPreProductContext.externalCategoryOptions(),
                externalMallPreProductContext.gptOptionsResult(),
                products,
                externalMallPreProductContext.standardSizes()
        );
    }


}
