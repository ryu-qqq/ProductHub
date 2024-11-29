package com.ryuqq.setof.domain.core.product.gpt;

import com.ryuqq.setof.enums.core.ProductDataType;
import com.ryuqq.setof.support.utils.AbstractProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BatchResultServiceProvider extends AbstractProvider<ProductDataType, GptBatchResultCommandService<? extends GptBatchResult>> {

    public BatchResultServiceProvider(List<GptBatchResultCommandService<? extends GptBatchResult>> services) {
        for (GptBatchResultCommandService<? extends GptBatchResult> service : services) {
            map.put(service.getProductDataType(), service);
        }
    }

}