package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.storage.db.core.site.external.ExternalProductPolicyQueryRepository;
import com.ryuqq.setof.storage.db.core.site.external.ExternalProductQueryRepository;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductContextDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalProductFinder implements ExternalProductQueryService{

    private final ExternalProductQueryRepository externalProductQueryRepository;
    private final ExternalProductPolicyQueryRepository externalProductPolicyQueryRepository;

    public ExternalProductFinder(ExternalProductQueryRepository externalProductQueryRepository, ExternalProductPolicyQueryRepository externalProductPolicyQueryRepository) {
        this.externalProductQueryRepository = externalProductQueryRepository;
        this.externalProductPolicyQueryRepository = externalProductPolicyQueryRepository;
    }

    @Override
    public List<Long> findUnlinkedProductGroupIds(long sellerId, List<Long> siteIds){
        return externalProductQueryRepository.fetchUnlinkedProductGroupIdsBySellerIdAndSiteId(sellerId, siteIds);
    }

    @Override
    public List<ExternalProductContext> findExternalProductContext(ExternalProductFilter externalProductFilter){
        List<ExternalProductContextDto> externalProductContextDtos = externalProductPolicyQueryRepository.fetchExternalProductContextByFilter(externalProductFilter.toExternalProductFilterDto());
        return externalProductContextDtos
                .stream()
                .map(e ->
                    new ExternalProductContext(
                            ExternalProductGroup.from(e.getExternalProductGroupDto()),
                            ExternalProductPolicy.from(e.getExternalProductGroupDto().getPolicyId(), e.getExternalProductGroupDto().getSiteId(), e.getExternalProductPolicyDto()),
                            e.getExternalProductProcessingResultDto().stream().map(ExternalProductProcessingResult::from).toList()
                    )
                )
                .toList();
    }

}
