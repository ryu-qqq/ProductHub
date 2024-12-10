package com.ryuqq.setof.support.external.core.shein;

import com.ryuqq.setof.support.external.core.ExternalSyncCategoryOption;
import com.ryuqq.setof.support.external.core.ExternalSyncOptionResult;
import com.ryuqq.setof.support.external.core.ExternalSyncProduct;
import com.ryuqq.setof.support.external.core.ExternalSyncStandardSize;
import com.ryuqq.setof.support.external.core.shein.domain.*;
import com.ryuqq.setof.support.utils.Money;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class SheInOptionMapper {

    private final SheInPriceGenerator sheInPriceGenerator;

    public SheInOptionMapper(SheInPriceGenerator sheInPriceGenerator) {
        this.sheInPriceGenerator = sheInPriceGenerator;
    }

    public SheInOptionContext getAttributes(long productGroupId, String styleCode, Money currentPrice, List<ExternalSyncCategoryOption> categoryOptions, ExternalSyncOptionResult optionResult, List<ExternalSyncProduct> products, List<ExternalSyncStandardSize> externalSyncStandardSizes){
        List<SheInSku> skus = products.stream().map(p ->
                new SheInSku(300, 400, 1200, 1500, 1, 1,
                        getSupplierSku(styleCode, p.option()),
                        getSheInPrice(currentPrice, p),
                        getSheInStock(p.quantity()),
                        getSheInProductAttribute(p, categoryOptions, externalSyncStandardSizes)
                )
        ).toList();

        return new SheInOptionContext(skus);
    }

    private String getSupplierSku(String styleCode, String optionName){
        if(styleCode != null && !styleCode.isBlank()) return styleCode + "_" + optionName;

        return optionName;
    }

    private List<SheInPrice> getSheInPrice(Money currentPrice, ExternalSyncProduct externalSyncProduct){
        Money plusPrice = currentPrice.plus(externalSyncProduct.additionalPrice());
        BigDecimal basePrice = sheInPriceGenerator.calculateFinalPrice(plusPrice, BigDecimal.valueOf(1415));
        return List.of(new SheInPrice(basePrice, "USD", "shein-us"));
    }

    private List<SheInStock> getSheInStock(int quantity){
        return List.of(new SheInStock(quantity));
    }


    private List<SheInProductAttribute> getSheInProductAttribute(ExternalSyncProduct externalSyncProduct, List<ExternalSyncCategoryOption> categoryOptions, List<ExternalSyncStandardSize> externalSyncStandardSizes ){



        return List.of();
        //return List.of(new SheInProductAttribute());
    }



}
