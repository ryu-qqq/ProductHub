package com.ryuqq.setof.support.external.core.sellic;

import com.ryuqq.setof.enums.core.OptionType;
import com.ryuqq.setof.support.external.core.ExternalMallOptionContext;
import com.ryuqq.setof.support.external.core.ExternalSyncProduct;
import com.ryuqq.setof.support.external.core.sellic.dto.SellicOptionContextDto;
import com.ryuqq.setof.support.external.core.sellic.dto.SellicOptionDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SellicOptionMapper {

    public SellicOptionContextDto generateOptionContext(OptionType optionType, ExternalMallOptionContext externalMallOptionContext) {
        String optionName1 = resolveOptionName1(optionType, externalMallOptionContext);
        String optionName2 = resolveOptionName2(optionType, externalMallOptionContext);

        List<SellicOptionDto> sellicOptionDtos = externalMallOptionContext.products().stream()
                .map(this::toSellicOptionDto)
                .toList();

        return new SellicOptionContextDto(sellicOptionDtos, optionName1, optionName2);
    }

    private String resolveOptionName1(OptionType optionType, ExternalMallOptionContext context) {
        if (!optionType.isMultiOption()) {
            return "단품";
        }

        return context.products().getFirst().getOption(0).optionName().name();
    }

    private String resolveOptionName2(OptionType optionType, ExternalMallOptionContext context) {
        if (optionType.isTwoDepthOption()) {
            return context.products().getFirst().getOption(1).optionName().name();
        }

        return "";
    }

    private SellicOptionDto toSellicOptionDto(ExternalSyncProduct product) {
        String optionData1 = "SINGLE";
        String optionData2 = "";

        if (product.options() != null && !product.options().isEmpty()) {
            optionData1 = product.getOption(0).optionValue();
            if (product.options().size() > 1) {
                optionData2 = product.getOption(1).optionValue();
            }
        }

        return new SellicOptionDto(
                optionData1,
                optionData2,
                "",
                0,
                product.additionalPrice().getAmount(),
                product.quantity()
        );
    }


}
