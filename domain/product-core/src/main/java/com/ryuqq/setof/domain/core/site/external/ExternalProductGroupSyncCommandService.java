package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.product.ProductGroupNameCommandService;
import com.ryuqq.setof.storage.db.core.site.external.ExternalProductGroupEntity;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ExternalProductGroupSyncCommandService {

    private final ProductGroupNameCommandService productGroupNameCommandService;
    private final ExternalProductGroupQueryService externalProductGroupQueryService;
    private final ExternalPolicyContextQueryService externalPolicyContextQueryService;
    private final ExternalProductGroupCommandService externalProductGroupCommandService;
    private final ExternalSiteSellerRelationQueryService externalSiteSellerRelationQueryService;

    public ExternalProductGroupSyncCommandService(ProductGroupNameCommandService productGroupNameCommandService, ExternalProductGroupQueryService externalProductGroupQueryService, ExternalPolicyContextQueryService externalPolicyContextQueryService, ExternalProductGroupCommandService externalProductGroupCommandService, ExternalSiteSellerRelationQueryService externalSiteSellerRelationQueryService) {
        this.productGroupNameCommandService = productGroupNameCommandService;
        this.externalProductGroupQueryService = externalProductGroupQueryService;
        this.externalPolicyContextQueryService = externalPolicyContextQueryService;
        this.externalProductGroupCommandService = externalProductGroupCommandService;
        this.externalSiteSellerRelationQueryService = externalSiteSellerRelationQueryService;
    }


    @Transactional
    public long syncExternalProducts(ExternalSiteSellerRelationCommand command) {
        List<Long> validSiteIds = getValidSiteIds(command);
        if (validSiteIds.isEmpty()) {
            return 0L;
        }

        List<Long> unlinkedProductGroupIds = externalProductGroupQueryService.fetchUnlinkedProductGroupIds(
                command.sellerId(), command.siteIds());

        if (unlinkedProductGroupIds.isEmpty()) {
            return 0L;
        }

        Map<Long, ExternalPolicyContext> sitePolicyMap = toSiteIdMap(command.siteIds());
        List<ExternalProductGroupEntity> externalProductEntities = generateExternalProductEntities(command.siteIds(), unlinkedProductGroupIds, sitePolicyMap);

        externalProductGroupCommandService.saveAllExternalProductGroup(externalProductEntities);
        productGroupNameCommandService.insertAll(command.siteIds(), unlinkedProductGroupIds);

        return externalProductEntities.size();
    }

    private List<Long> getValidSiteIds(ExternalSiteSellerRelationCommand command) {
        return command.siteIds().stream()
                .filter(siteId -> externalSiteSellerRelationQueryService.existBySellerIdAndSitId(command.sellerId(), siteId))
                .toList();
    }

    private Map<Long, ExternalPolicyContext> toSiteIdMap(List<Long> siteIds) {
        return externalPolicyContextQueryService.fetchByIds(siteIds).stream()
                .collect(Collectors.toMap(ExternalPolicyContext::getSiteId, Function.identity()));
    }

    private List<ExternalProductGroupEntity> generateExternalProductEntities(
            List<Long> siteIds,
            List<Long> productGroupIds,
            Map<Long, ExternalPolicyContext> sitePolicyMap) {

        return siteIds.stream()
                .flatMap(siteId -> productGroupIds.stream()
                        .map(productGroupId -> {
                            ExternalPolicyContext policy = sitePolicyMap.get(siteId);
                            if (policy != null) {
                                return ExternalProductGroupEntity.toWaitingStatusEntity(siteId, productGroupId, policy.getPolicyId());
                            }
                            return null;
                        }))
                .filter(Objects::nonNull)
                .toList();
    }


}
