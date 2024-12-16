package com.ryuqq.setof.support.external.core.buyma;

import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.*;
import com.ryuqq.setof.support.external.core.buyma.dto.BuyMaImageDto;
import com.ryuqq.setof.support.external.core.buyma.dto.BuyMaOptionContextDto;
import com.ryuqq.setof.support.external.core.buyma.dto.BuyMaProductInsertRequestDto;
import com.ryuqq.setof.support.external.core.buyma.dto.BuyMaProductPayload;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class BuyMaProductMapper extends AbstractExternalMallContextMapper {

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


    public BuyMaProductInsertRequestDto toInsertRequestDto(ExternalMallProductContext externalMallProductContext){

        ExternalMallNameContext nameContext = externalMallProductContext.getNameContext();
        ExternalMallCategoryAndBrandContext categoryAndBrandContext = externalMallProductContext.getCategoryAndBrandContext();
        ExternalMallPriceContext priceContext = externalMallProductContext.getPriceContext();
        List<BuyMaImageDto> buyMaImages = buyMaImageGenerator.toBuyMaImageDto(externalMallProductContext.getImageGroupContext());
        BuyMaOptionContextDto buyMaOptionContextDto = buyMaOptionMapper.toBuyMaOptionContext(externalMallProductContext.getOptionContext());

        BuyMaProductPayload buyMaProductPayload = new BuyMaProductPayload(
                nameContext.styleCode(), nameContext.productGroupId(), nameContext.name(), nameContext.description(),
                categoryAndBrandContext.brandId(), categoryAndBrandContext.brandName(), categoryAndBrandContext.categoryId(),
                priceContext.currentPrice().intValue(), priceContext.regularPrice().intValue(), buyMaImages,
                buyMaOptionContextDto.buyMaVariants(), buyMaOptionContextDto.buyMaOptions(), externalMallProductContext.getExternalProductGroupId(), ""
        );

        return new BuyMaProductInsertRequestDto(buyMaProductPayload);
    }

    @Override
    protected ExternalMallNameContext generateNameContext(ExternalMallPreProductContext externalMallPreProductContext) {
        String brandName =  externalMallPreProductContext.brand().brandName();
        ExternalSyncProductGroup productGroup = externalMallPreProductContext.productGroup();
        return buyMaProductNameContextGenerator.generateBuyMaProductDetail(brandName, productGroup);
    }

    @Override
    protected ExternalMallImageContext generateImageContext(ExternalMallPreProductContext externalMallPreProductContext) {
        return buyMaImageGenerator.generateImageContext(externalMallPreProductContext.productGroup().images());
    }


    @Override
    protected ExternalMallPriceContext generatePriceHolder(ExternalMallPreProductContext externalMallPreProductContext) {
        ExternalSyncProductGroup productGroup = externalMallPreProductContext.productGroup();
        BigDecimal finalPrice = buyMaPriceGenerator.calculateFinalPrice(productGroup.currentPrice(), BigDecimal.valueOf(907));
        BigDecimal referencePrice = finalPrice.multiply(BigDecimal.valueOf(1.5));
        return new ExternalMallPriceContext(referencePrice, finalPrice, Origin.JP);
    }


}
