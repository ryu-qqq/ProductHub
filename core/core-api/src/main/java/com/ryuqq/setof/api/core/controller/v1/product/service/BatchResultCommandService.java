package com.ryuqq.setof.api.core.controller.v1.product.service;

import com.ryuqq.setof.api.core.controller.v1.product.request.GptBatchSaveRequest;
import com.ryuqq.setof.domain.core.product.gpt.GptBatchResult;
import com.ryuqq.setof.domain.core.product.gpt.BatchResultServiceProvider;
import com.ryuqq.setof.domain.core.product.gpt.GptBatchResultCommandService;
import org.springframework.stereotype.Component;

@Component
public class BatchResultCommandService {

    private final BatchResultServiceProvider batchResultServiceProvider;

    public BatchResultCommandService(BatchResultServiceProvider batchResultServiceProvider) {
        this.batchResultServiceProvider = batchResultServiceProvider;
    }

    @SuppressWarnings("unchecked")
    public void batchResultIntegration(GptBatchSaveRequest requestDto) {
        GptBatchResultCommandService<GptBatchResult> gptBatchResultCommandService =
                (GptBatchResultCommandService<GptBatchResult>) batchResultServiceProvider.get(requestDto.getDataType());

        GptBatchResult domain = requestDto.toDomain();
        gptBatchResultCommandService.execute(domain);
    }


}
