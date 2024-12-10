package com.ryuqq.setof.support.external.core.sellic;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.*;
import com.ryuqq.setof.support.external.core.sellic.dto.SellicOptionContextDto;
import com.ryuqq.setof.support.external.core.sellic.dto.SellicProductInsertRequestDto;
import com.ryuqq.setof.support.external.core.sellic.dto.SellicProductPayload;
import com.ryuqq.setof.support.utils.Money;
import org.springframework.stereotype.Component;

@Component
public class SellicProductMapper implements ExternalMallContextMapper {

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


    private ExternalMallNameContext generateNameContext(ExternalMallPreProductContext externalMallPreProductContext) {
        String brandName =  externalMallPreProductContext.brand().brandName();
        ExternalSyncProductGroup productGroup = externalMallPreProductContext.productGroup();
        return sellicProductNameContextGenerator.generateSellicProductDetail(brandName, productGroup);
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
        return sellicImageGenerator.generateImageContext(externalMallPreProductContext.productGroup().images());
    }

    private ExternalMallPriceContext generatePriceHolder(ExternalMallPreProductContext externalMallPreProductContext) {
        Money regularPrice = externalMallPreProductContext.productGroup().regularPrice();
        Money currentPrice = externalMallPreProductContext.productGroup().currentPrice();
        return sellicPriceGenerator.calculateFinalPrice(regularPrice, currentPrice);
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
