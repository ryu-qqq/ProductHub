package com.ryuqq.setof.api.core.controller.v1.product.service;

import com.ryuqq.setof.api.core.controller.v1.product.request.BatchSaveRequest;
import com.ryuqq.setof.domain.core.product.gpt.BatchResult;
import com.ryuqq.setof.domain.core.product.gpt.BatchResultCommandService;
import com.ryuqq.setof.domain.core.product.gpt.BatchResultServiceProvider;
import org.springframework.stereotype.Component;

@Component
public class BatchResultCommandFacade {

    private final BatchResultServiceProvider batchResultServiceProvider;

    public BatchResultCommandFacade(BatchResultServiceProvider batchResultServiceProvider) {
        this.batchResultServiceProvider = batchResultServiceProvider;
    }

    public void batchResultIntegration(BatchSaveRequest requestDto) {
        BatchResultCommandService<BatchResult> batchResultCommandService =
                (BatchResultCommandService<BatchResult>) batchResultServiceProvider.get(requestDto.getDataType());

        BatchResult domain = requestDto.toDomain();
        batchResultCommandService.execute(domain);
    }


}
