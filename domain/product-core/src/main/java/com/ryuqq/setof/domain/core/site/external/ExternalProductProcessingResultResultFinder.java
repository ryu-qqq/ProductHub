package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.ProductDataType;
import com.ryuqq.setof.db.core.product.dto.ProductProcessingResultDto;
import com.ryuqq.setof.db.core.product.gpt.ProductProcessingQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ExternalProductProcessingResultResultFinder implements ExternalProductProcessingResultQueryService {

    private final ProductProcessingQueryRepository productProcessingQueryRepository;

    public ExternalProductProcessingResultResultFinder(ProductProcessingQueryRepository productProcessingQueryRepository) {
        this.productProcessingQueryRepository = productProcessingQueryRepository;
    }

    @Override
    public Optional<ExternalProductProcessingResult> fetchByProductGroupIdAndDataType(long productGroupId, ProductDataType productDataType) {
        Optional<ProductProcessingResultDto> externalProcessingResultOpt = productProcessingQueryRepository.fetchByProductGroupIdAndDataType(productGroupId, productDataType);
        if(externalProcessingResultOpt.isPresent()){
            ProductProcessingResultDto productProcessingResultDto = externalProcessingResultOpt.get();
            return Optional.of(ExternalProductProcessingResult.from(productProcessingResultDto));
        }
        return Optional.empty();
    }

    @Override
    public List<ExternalProductProcessingResult> fetchByProductGroupIdsAndDataType(List<Long> productGroupIds, ProductDataType productDataType) {
        return productProcessingQueryRepository.fetchByProductGroupIdsAndDataType(productGroupIds, productDataType)
                .stream()
                .map(p -> new ExternalProductProcessingResult(
                        p.getProductGroupId(),
                        p.getProductDataType(),
                        p.getResult()
                ))
                .toList();

    }

}
