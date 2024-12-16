package com.ryuqq.setof.support.external.core;

public abstract class AbstractExternalMallContextMapper implements ExternalMallContextMapper {

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


    protected ExternalMallCategoryAndBrandContext generateCategoryAndBrand(ExternalMallPreProductContext externalMallPreProductContext) {
        String categoryId = externalMallPreProductContext.category().externalCategoryId();
        String extraCategoryId = externalMallPreProductContext.category().externalExtraCategoryId();
        ExternalSyncBrand brand = externalMallPreProductContext.brand();

        if(categoryId ==null || categoryId.isBlank() || brand == null) {
            throw new IllegalArgumentException("Mapping Brand or Category Id is null");
        }

        if(extraCategoryId == null || extraCategoryId.isBlank() || extraCategoryId.isEmpty()) {
            extraCategoryId = "";
        }

        return new ExternalMallCategoryAndBrandContext(categoryId, extraCategoryId, brand.externalBrandId(), brand.brandName());
    }


    protected ExternalMallOptionContext generateOptionContext(ExternalMallPreProductContext externalMallPreProductContext){
        return new ExternalMallOptionContext(
                externalMallPreProductContext.getSetOfProductGroupId(),
                externalMallPreProductContext.productGroup().optionType(),
                externalMallPreProductContext.products(),
                externalMallPreProductContext.externalCategoryOptions(),
                externalMallPreProductContext.gptOptionsResult(),
                externalMallPreProductContext.standardSizes()
        );
    }



    protected ExternalMallProductStatusContext generateProductStatusContext(ExternalMallPreProductContext externalMallPreProductContext){
        return new ExternalMallProductStatusContext(
                externalMallPreProductContext.productGroup().soldOutYn(),
                externalMallPreProductContext.productGroup().displayYn()
        );
    }

    protected abstract ExternalMallNameContext generateNameContext(ExternalMallPreProductContext externalMallPreProductContext);
    protected abstract ExternalMallImageContext generateImageContext(ExternalMallPreProductContext externalMallPreProductContext);
    protected abstract ExternalMallPriceContext generatePriceHolder(ExternalMallPreProductContext externalMallPreProductContext);

}
