package com.ryuqq.setof.support.external.core.shein;

import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.*;
import com.ryuqq.setof.support.external.core.dto.SheInImageGroupDto;
import com.ryuqq.setof.support.external.core.dto.SheInProductInsertRequestDto;
import com.ryuqq.setof.support.external.core.dto.SheInSkuDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SheInProductMapper  extends AbstractExternalMallContextMapper {

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


    public SheInProductInsertRequestDto toInsertRequestDto(ExternalMallProductContext externalMallProductContext){

        ExternalMallNameContext nameContext = externalMallProductContext.getNameContext();
        ExternalMallCategoryAndBrandContext categoryAndBrandContext = externalMallProductContext.getCategoryAndBrandContext();
        ExternalMallPriceContext priceContext = externalMallProductContext.getPriceContext();
        SheInImageGroupDto sheInImageGroupDto = sheInImageGenerator.toSheInImageGroupDto(externalMallProductContext.getImageGroupContext());
        List<SheInSkuDto> attributes = sheInOptionMapper.getAttributes(nameContext.styleCode(), priceContext, externalMallProductContext.getOptionContext());

        return new SheInProductInsertRequestDto(
                categoryAndBrandContext.brandId(), categoryAndBrandContext.categoryId(), categoryAndBrandContext.extraCategoryId(),
                nameContext.styleCode(), nameContext.productGroupId(), nameContext.name(),
                sheInImageGroupDto, attributes
        );

    }


    @Override
    protected ExternalMallNameContext generateNameContext(ExternalMallPreProductContext externalMallPreProductContext) {
        String brandName =  externalMallPreProductContext.brand().brandName();
        ExternalSyncProductGroup productGroup = externalMallPreProductContext.productGroup();
        return sheInProductNameContextGenerator.generateBuyMaProductDetail(brandName, productGroup);
    }

    @Override
    protected ExternalMallImageContext generateImageContext(ExternalMallPreProductContext externalMallPreProductContext) {
        return sheInImageGenerator.generateImageContext(externalMallPreProductContext.productGroup().images());
    }

    @Override
    protected ExternalMallPriceContext generatePriceHolder(ExternalMallPreProductContext externalMallPreProductContext) {
        ExternalSyncProductGroup productGroup = externalMallPreProductContext.productGroup();
        BigDecimal finalPrice = sheInPriceGenerator.calculateFinalPrice(productGroup.currentPrice(), BigDecimal.valueOf(1432.6));
        BigDecimal referencePrice = finalPrice.multiply(BigDecimal.valueOf(1.5));
        return new ExternalMallPriceContext(referencePrice, finalPrice, Origin.JP);
    }

}
