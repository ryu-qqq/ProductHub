package com.ryuqq.setof.support.external.core.buyma;

import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.*;
import com.ryuqq.setof.support.external.core.buyma.dto.BuyMaOptionContextDto;
import com.ryuqq.setof.support.external.core.buyma.dto.BuyMaImageDto;
import com.ryuqq.setof.support.external.core.buyma.dto.BuyMaProductInsertRequestDto;
import com.ryuqq.setof.support.external.core.buyma.dto.BuyMaProductPayload;
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
                buyMaOptionContextDto.buyMaVariants(), buyMaOptionContextDto.buyMaOptions(), null, ""
        );

        return new BuyMaProductInsertRequestDto(buyMaProductPayload);

    }


    private ExternalMallNameContext generateNameContext(ExternalMallPreProductContext externalMallPreProductContext) {
        String brandName =  externalMallPreProductContext.brand().brandName();
        ExternalSyncProductGroup productGroup = externalMallPreProductContext.productGroup();
        return buyMaProductNameContextGenerator.generateBuyMaProductDetail(brandName, productGroup);
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
        return buyMaImageGenerator.generateImageContext(externalMallPreProductContext.productGroup().images());
    }


    private ExternalMallPriceContext generatePriceHolder(ExternalMallPreProductContext externalMallPreProductContext) {
        ExternalSyncProductGroup productGroup = externalMallPreProductContext.productGroup();
        BigDecimal finalPrice = buyMaPriceGenerator.calculateFinalPrice(productGroup.currentPrice(), BigDecimal.valueOf(907));
        BigDecimal referencePrice = finalPrice.multiply(BigDecimal.valueOf(1.5));
        return new ExternalMallPriceContext(referencePrice, finalPrice, Origin.JP);
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
