package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.product.ProductGroupNameCommandService;
import com.ryuqq.setof.storage.db.core.site.external.ExternalProductEntity;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ExternalProductSyncCommandService {

    private final ProductGroupNameCommandService productGroupNameCommandService;
    private final ExternalProductQueryService externalProductQueryService;
    private final ExternalPolicyContextQueryService externalPolicyContextQueryService;
    private final ExternalProductCommandService externalProductCommandService;
    private final ExternalSiteSellerRelationQueryService externalSiteSellerRelationQueryService;

    public ExternalProductSyncCommandService(ProductGroupNameCommandService productGroupNameCommandService, ExternalProductQueryService externalProductQueryService, ExternalPolicyContextQueryService externalPolicyContextQueryService, ExternalProductCommandService externalProductCommandService, ExternalSiteSellerRelationQueryService externalSiteSellerRelationQueryService) {
        this.productGroupNameCommandService = productGroupNameCommandService;
        this.externalProductQueryService = externalProductQueryService;
        this.externalPolicyContextQueryService = externalPolicyContextQueryService;
        this.externalProductCommandService = externalProductCommandService;
        this.externalSiteSellerRelationQueryService = externalSiteSellerRelationQueryService;
    }


    @Transactional
    public long syncExternalProducts(ExternalSiteSellerRelationCommand command) {
        List<Long> validSiteIds = getValidSiteIds(command);
        if (validSiteIds.isEmpty()) {
            return 0L;
        }

        List<Long> unlinkedProductGroupIds = externalProductQueryService.fetchUnlinkedProductGroupIds(
                command.sellerId(), command.siteIds());

        if (unlinkedProductGroupIds.isEmpty()) {
            return 0L;
        }

        Map<Long, ExternalPolicyContext> sitePolicyMap = toSiteIdMap(command.siteIds());
        List<ExternalProductEntity> externalProductEntities = generateExternalProductEntities(command.siteIds(), unlinkedProductGroupIds, sitePolicyMap);

        externalProductCommandService.saveAllExternalProduct(externalProductEntities);
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

    private List<ExternalProductEntity> generateExternalProductEntities(
            List<Long> siteIds,
            List<Long> productGroupIds,
            Map<Long, ExternalPolicyContext> sitePolicyMap) {

        return siteIds.stream()
                .flatMap(siteId -> productGroupIds.stream()
                        .map(productGroupId -> {
                            ExternalPolicyContext policy = sitePolicyMap.get(siteId);
                            if (policy != null) {
                                return ExternalProductEntity.toWaitingStatusEntity(siteId, productGroupId, policy.getPolicyId());
                            }
                            return null;
                        }))
                .filter(Objects::nonNull)
                .toList();
    }


}
