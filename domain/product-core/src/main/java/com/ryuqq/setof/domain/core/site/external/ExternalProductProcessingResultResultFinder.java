package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.ProductDataType;
import com.ryuqq.setof.storage.db.core.site.external.ExternalProcessingQueryRepository;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductProcessingResultDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExternalProductProcessingResultResultFinder implements ExternalProductProcessingResultQueryService {

    private final ExternalProcessingQueryRepository externalProcessingQueryRepository;

    public ExternalProductProcessingResultResultFinder(ExternalProcessingQueryRepository externalProcessingQueryRepository) {
        this.externalProcessingQueryRepository = externalProcessingQueryRepository;
    }

    @Override
    public Optional<ExternalProductProcessingResult> findExternalProductProcessing(long productGroupId, ProductDataType productDataType) {
        Optional<ExternalProductProcessingResultDto> externalProcessingResultOpt = externalProcessingQueryRepository.fetchByProductGroupIdAndDataType(productGroupId, productDataType);
        if(externalProcessingResultOpt.isPresent()){
            ExternalProductProcessingResultDto externalProductProcessingResultDto = externalProcessingResultOpt.get();
            return Optional.of(ExternalProductProcessingResult.from(externalProductProcessingResultDto));
        }

        return Optional.empty();
    }
}
