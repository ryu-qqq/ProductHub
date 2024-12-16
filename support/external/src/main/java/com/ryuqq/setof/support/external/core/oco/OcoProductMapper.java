package com.ryuqq.setof.support.external.core.oco;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.*;
import com.ryuqq.setof.support.external.core.oco.dto.OcoImageDto;
import com.ryuqq.setof.support.external.core.oco.dto.OcoOptionContextDto;
import com.ryuqq.setof.support.external.core.oco.dto.OcoProductInsertRequestDto;
import com.ryuqq.setof.support.external.core.oco.dto.OcoProductPayload;
import com.ryuqq.setof.support.utils.Money;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OcoProductMapper extends AbstractExternalMallContextMapper {

    private final OcoImageGenerator ocoImageGenerator;
    private final OcoPriceGenerator ocoPriceGenerator;
    private final OcoProductNameContextGenerator ocoProductNameContextGenerator;
    private final OcoOptionMapper ocoOptionMapper;

    public OcoProductMapper(OcoImageGenerator ocoImageGenerator, OcoPriceGenerator ocoPriceGenerator, OcoProductNameContextGenerator ocoProductNameContextGenerator, OcoOptionMapper ocoOptionMapper) {
        this.ocoImageGenerator = ocoImageGenerator;
        this.ocoPriceGenerator = ocoPriceGenerator;
        this.ocoProductNameContextGenerator = ocoProductNameContextGenerator;
        this.ocoOptionMapper = ocoOptionMapper;
    }

    @Override
    public SiteName getSiteName() {
        return SiteName.OCO;
    }


    public OcoProductInsertRequestDto toInsertRequestDto(ExternalMallPreProductContext externalMallPreProductContext, ExternalMallProductContext externalMallProductContext){

        OcoOptionContextDto ocoOptionContextDto = ocoOptionMapper.generateOptionContext(externalMallProductContext.getOptionContext());
        List<OcoImageDto> ocoImageDto = ocoImageGenerator.toOcoImageDto(externalMallProductContext.getImageGroupContext());

        OcoProductPayload ocoProductPayload = new OcoProductPayload(
                externalMallPreProductContext.productGroup(),
                externalMallProductContext.getNameContext(),
                externalMallProductContext.getCategoryAndBrandContext(),
                externalMallProductContext.getPriceContext(),
                ocoOptionContextDto,
                ocoImageDto
                );

        return new OcoProductInsertRequestDto(ocoProductPayload);
    }


    @Override
    protected ExternalMallNameContext generateNameContext(ExternalMallPreProductContext externalMallPreProductContext) {
        String brandName =  externalMallPreProductContext.brand().brandName();
        ExternalSyncProductGroup productGroup = externalMallPreProductContext.productGroup();
        return ocoProductNameContextGenerator.generateBuyMaProductDetail(brandName, productGroup);
    }

    @Override
    protected ExternalMallImageContext generateImageContext(ExternalMallPreProductContext externalMallPreProductContext) {
        return ocoImageGenerator.generateImageContext(externalMallPreProductContext.productGroup().images());
    }

    @Override
    protected ExternalMallPriceContext generatePriceHolder(ExternalMallPreProductContext externalMallPreProductContext) {
        Money regularPrice = externalMallPreProductContext.productGroup().regularPrice();
        Money currentPrice = externalMallPreProductContext.productGroup().currentPrice();
        return ocoPriceGenerator.calculateFinalPrice(regularPrice, currentPrice);
    }



}
