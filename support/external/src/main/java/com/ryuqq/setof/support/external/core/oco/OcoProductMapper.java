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
public class OcoProductMapper implements ExternalMallContextMapper {

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

    @Override
    public ExternalMallProductContext.Builder toExternalMallProductContextBuilder(ExternalMallPreProductContext externalMallPreProductContext) {
        long productGroupId = externalMallPreProductContext.getProductGroupId();
        long setOfProductGroupId = externalMallPreProductContext.getSetOfProductGroupId();

        ExternalMallNameContext externalMallNameContext = generateNameContext(externalMallPreProductContext);
        ExternalMallCategoryAndBrandContext externalMallCategoryAndBrandContext = generateCategoryAndBrand(externalMallPreProductContext);
        ExternalMallImageContext externalMallImageContext = generateImageContext(externalMallPreProductContext);
        ExternalMallPriceContext externalMallPriceContext = generatePriceHolder(externalMallPreProductContext);
        ExternalMallOptionContext externalMallOptionContext = generateOptionContext(externalMallPreProductContext);
        ExternalMallProductStatusContext externalMallProductStatusContext = generateProductStatusContext(externalMallPreProductContext);

        return new ExternalMallProductContext.Builder()
                .withNames(productGroupId, setOfProductGroupId, externalMallNameContext)
                .withCategoryAndBrand(externalMallCategoryAndBrandContext)
                .withImages(externalMallImageContext)
                .withPrice(externalMallPriceContext)
                .withOptions(externalMallOptionContext)
                .withProductStatus(externalMallProductStatusContext);

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



    private ExternalMallNameContext generateNameContext(ExternalMallPreProductContext externalMallPreProductContext) {
        String brandName =  externalMallPreProductContext.brand().brandName();
        ExternalSyncProductGroup productGroup = externalMallPreProductContext.productGroup();
        return ocoProductNameContextGenerator.generateBuyMaProductDetail(brandName, productGroup);
    }

    private ExternalMallCategoryAndBrandContext generateCategoryAndBrand(ExternalMallPreProductContext externalMallPreProductContext) {
        String categoryId = externalMallPreProductContext.category().externalCategoryId();
        ExternalSyncBrand brand = externalMallPreProductContext.brand();

        if(categoryId ==null || categoryId.isBlank() || brand == null) {
            throw new IllegalArgumentException("Mapping Brand or Category Id is null");
        }

        return new ExternalMallCategoryAndBrandContext(categoryId, brand.externalBrandId(), brand.brandName());
    }


    private ExternalMallImageContext generateImageContext(ExternalMallPreProductContext externalMallPreProductContext) {
        return ocoImageGenerator.generateImageContext(externalMallPreProductContext.productGroup().images());
    }


    private ExternalMallPriceContext generatePriceHolder(ExternalMallPreProductContext externalMallPreProductContext) {
        Money regularPrice = externalMallPreProductContext.productGroup().regularPrice();
        Money currentPrice = externalMallPreProductContext.productGroup().currentPrice();
        return ocoPriceGenerator.calculateFinalPrice(regularPrice, currentPrice);
    }

    private ExternalMallOptionContext generateOptionContext(ExternalMallPreProductContext externalMallPreProductContext){
        return new ExternalMallOptionContext(
                externalMallPreProductContext.getSetOfProductGroupId(),
                externalMallPreProductContext.productGroup().optionType(),
                externalMallPreProductContext.products(),
                externalMallPreProductContext.externalCategoryOptions(),
                externalMallPreProductContext.gptOptionsResult(),
                externalMallPreProductContext.standardSizes()
        );
    }

    private ExternalMallProductStatusContext generateProductStatusContext(ExternalMallPreProductContext externalMallPreProductContext){
        return new ExternalMallProductStatusContext(
                externalMallPreProductContext.productGroup().soldOutYn(),
                externalMallPreProductContext.productGroup().displayYn()
        );
    }

}
