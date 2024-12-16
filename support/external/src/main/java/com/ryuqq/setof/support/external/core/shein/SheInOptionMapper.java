package com.ryuqq.setof.support.external.core.shein;

import com.ryuqq.setof.enums.core.SizeOrigin;
import com.ryuqq.setof.support.external.core.*;
import com.ryuqq.setof.support.external.core.dto.SheInPriceDto;
import com.ryuqq.setof.support.external.core.dto.SheInProductAttributeDto;
import com.ryuqq.setof.support.external.core.dto.SheInSkuDto;
import com.ryuqq.setof.support.external.core.dto.SheInStockDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SheInOptionMapper {

    private final static String ONE_SIZE = "one-size";

    public List<SheInSkuDto> getAttributes(String styleCode, ExternalMallPriceContext priceContext, ExternalMallOptionContext externalMallOptionContext){
        return externalMallOptionContext.products().stream().map(p ->
                new SheInSkuDto(300, 400, 1200, 1500, 1, 1,
                        getSupplierSku(styleCode, p.option()),
                        getSheInPrice(priceContext, p),
                        getSheInStock(p.quantity()),
                        getSheInProductAttribute(p, externalMallOptionContext)
                )
        ).toList();
    }

    private String getSupplierSku(String styleCode, String optionValue){
        if(styleCode != null && !styleCode.isBlank()) return styleCode + "_" + optionValue;

        return optionValue;
    }

    private List<SheInPriceDto> getSheInPrice(ExternalMallPriceContext priceContext, ExternalSyncProduct externalSyncProduct){
        BigDecimal basePrice = priceContext.currentPrice().add(externalSyncProduct.additionalPrice().getAmount());
        return List.of(new SheInPriceDto(basePrice, "USD", "shein-us"));
    }

    private List<SheInStockDto> getSheInStock(int quantity){
        return List.of(new SheInStockDto(quantity));
    }


    private List<SheInProductAttributeDto> getSheInProductAttribute(ExternalSyncProduct externalSyncProduct, ExternalMallOptionContext externalMallOptionContext){
        List<ExternalSyncCategoryOption> externalSyncCategoryOptions = externalMallOptionContext.externalCategoryOptions();
        List<ExternalSyncStandardSize> externalSyncStandardSizes = externalMallOptionContext.standardSizes();

        if(!externalMallOptionContext.optionType().isMultiOption()){
            Optional<ExternalSyncCategoryOption> externalSyncCategoryOptionOpt = externalSyncCategoryOptions.stream()
                    .filter(e -> e.optionValue().equals(ONE_SIZE))
                    .findFirst();

            if(externalSyncCategoryOptionOpt.isPresent()){
                ExternalSyncCategoryOption externalSyncCategoryOption = externalSyncCategoryOptionOpt.get();
                return List.of(new SheInProductAttributeDto(externalSyncCategoryOption.optionGroupId(), externalSyncCategoryOption.optionId()));
            }

            throw new IllegalArgumentException("Can't Match any option value");
        }

        String option = externalSyncProduct.option();
        ExternalSyncCategoryOption externalSyncCategoryOption = getExternalSyncCategoryOption(externalSyncStandardSizes, externalSyncCategoryOptions, option);

        return List.of(new SheInProductAttributeDto(externalSyncCategoryOption.optionGroupId(), externalSyncCategoryOption.optionId()));
    }

    public ExternalSyncCategoryOption getExternalSyncCategoryOption(List<ExternalSyncStandardSize> externalSyncStandardSizes, List<ExternalSyncCategoryOption> externalSyncCategoryOptions, String optionValue){
        Map<SizeOrigin, List<ExternalSyncStandardSize>> standardSizeRegionGroupMap = externalSyncStandardSizes.stream().collect(Collectors.groupingBy(ExternalSyncStandardSize::name));

        List<ExternalSyncStandardSize> standardSizes = standardSizeRegionGroupMap.get(SizeOrigin.STANDARD);
        int idx = 0;
        boolean match = false;

        for (ExternalSyncStandardSize externalSyncStandardSize : standardSizes) {
            String sizeValue = externalSyncStandardSize.sizeValue();

            String normalizedOptionValue = normalize(optionValue);
            String normalizedSizeValue = normalize(sizeValue);

            if(normalizedOptionValue.equals(normalizedSizeValue)){
                match = true;
                break;
            }

            idx++;
            if (idx == standardSizes.size()) break;

        }

        List<ExternalSyncStandardSize> usStandardSize = standardSizeRegionGroupMap.get(SizeOrigin.US);
        if(match && usStandardSize.size() > idx){
            ExternalSyncStandardSize externalSyncStandardSize = usStandardSize.get(idx);
            String regionSize = externalSyncStandardSize.getRegionSize();

            for(ExternalSyncCategoryOption externalSyncCategoryOption : externalSyncCategoryOptions){
                if(externalSyncCategoryOption.optionValue().equals(regionSize)) return externalSyncCategoryOption;
            }
        }

        throw new IllegalArgumentException("Can't Match any option value");
    }


    private String normalize(Object value) {
        if (value == null) {
            return "";
        }

        String stringValue = value.toString().trim();

        if (stringValue.matches("\\d+")) {
            return String.valueOf(Integer.parseInt(stringValue));
        }

        return stringValue;
    }

}
