package com.ryuqq.setof.support.external.core.oco;

import com.ryuqq.setof.enums.core.OptionType;
import com.ryuqq.setof.support.external.core.ExternalMallOptionContext;
import com.ryuqq.setof.support.external.core.ExternalMallProductContext;
import com.ryuqq.setof.support.external.core.ExternalSyncProduct;
import com.ryuqq.setof.support.external.core.oco.dto.OcoOptionDto;
import com.ryuqq.setof.support.external.core.oco.dto.OcoOptionContextDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OcoOptionMapper {

    public OcoOptionContextDto generateOptionContext(String externalProductGroupId, ExternalMallOptionContext externalMallOptionContext) {

        List<ExternalSyncProduct> products = externalMallOptionContext.products();
        OptionType optionType = externalMallOptionContext.optionType();

        Integer pid;

        if(externalProductGroupId != null && !externalProductGroupId.isBlank()) {
            pid = Integer.parseInt(externalProductGroupId);
        } else {
            pid = null;
        }


        List<OcoOptionDto> options = products.stream()
                .map(p -> {
                    String optionData2 = "";
                    if (p.options() != null && p.options().size() == 2) {
                        optionData2 = p.options().get(1).optionValue();
                    }
                    return new OcoOptionDto(
                            p.optionId() == null ? null : Integer.parseInt(p.optionId()),
                            pid,
                            p.option(),
                            optionData2,
                            p.quantity(),
                            "Y",
                            0

                    );
                })
                .toList();

        int optionLength = 0;
        if(optionType.isOneDepthOption()){
            optionLength = 1;
        }

        if(optionType.isTwoDepthOption()){
            optionLength = 2;
        }


        boolean hasOption = optionType.isMultiOption();

        return new OcoOptionContextDto(optionLength, hasOption, options);
    }
}
