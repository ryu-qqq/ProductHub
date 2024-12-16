package com.ryuqq.setof.support.external.core.sellic;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.*;
import com.ryuqq.setof.support.external.core.sellic.dto.SellicOptionContextDto;
import com.ryuqq.setof.support.external.core.sellic.dto.SellicProductInsertRequestDto;
import com.ryuqq.setof.support.external.core.sellic.dto.SellicProductPayload;
import com.ryuqq.setof.support.utils.Money;
import org.springframework.stereotype.Component;

@Component
public class SellicProductMapper extends AbstractExternalMallContextMapper {

    private final SellicImageGenerator sellicImageGenerator;
    private final SellicPriceGenerator sellicPriceGenerator;
    private final SellicProductNameContextGenerator sellicProductNameContextGenerator;
    private final SellicOptionMapper sellicOptionMapper;

    public SellicProductMapper(SellicImageGenerator sellicImageGenerator, SellicPriceGenerator sellicPriceGenerator, SellicProductNameContextGenerator sellicProductNameContextGenerator, SellicOptionMapper sellicOptionMapper) {
        this.sellicImageGenerator = sellicImageGenerator;
        this.sellicPriceGenerator = sellicPriceGenerator;
        this.sellicProductNameContextGenerator = sellicProductNameContextGenerator;
        this.sellicOptionMapper = sellicOptionMapper;
    }

    @Override
    public SiteName getSiteName() {
        return SiteName.SELLIC;
    }


    public SellicProductInsertRequestDto toInsertRequestDto(ExternalMallPreProductContext externalMallPreProductContext, ExternalMallProductContext externalMallProductContext){

        SellicOptionContextDto sellicOptionContextDto = sellicOptionMapper.generateOptionContext(externalMallPreProductContext.getOptionType(), externalMallProductContext.getOptionContext());

        SellicProductPayload sellicProductPayload = new SellicProductPayload(
                externalMallPreProductContext.productGroup(),
                externalMallProductContext.getNameContext(),
                externalMallProductContext.getCategoryAndBrandContext(),
                externalMallProductContext.getPriceContext(),
                sellicOptionContextDto,
                externalMallProductContext.getImageGroupContext().externalMallImages()
        );

        return new SellicProductInsertRequestDto(sellicProductPayload);
    }

    @Override
    protected ExternalMallNameContext generateNameContext(ExternalMallPreProductContext externalMallPreProductContext) {
        String brandName =  externalMallPreProductContext.brand().brandName();
        ExternalSyncProductGroup productGroup = externalMallPreProductContext.productGroup();
        return sellicProductNameContextGenerator.generateSellicProductDetail(brandName, productGroup);
    }

    @Override
    protected ExternalMallImageContext generateImageContext(ExternalMallPreProductContext externalMallPreProductContext) {
        return sellicImageGenerator.generateImageContext(externalMallPreProductContext.productGroup().images());
    }

    @Override
    protected ExternalMallPriceContext generatePriceHolder(ExternalMallPreProductContext externalMallPreProductContext) {
        Money regularPrice = externalMallPreProductContext.productGroup().regularPrice();
        Money currentPrice = externalMallPreProductContext.productGroup().currentPrice();
        return sellicPriceGenerator.calculateFinalPrice(regularPrice, currentPrice);
    }



}
