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
    private final ExternalSiteSellerRelationFinder externalSiteSellerRelationFinder;
    private final ExternalProductCommandService externalProductCommandService;
    private final ExternalPolicyContextFinder externalPolicyContextFinder;

    public ExternalProductSyncCommandService(ProductGroupNameCommandService productGroupNameCommandService, ExternalProductQueryService externalProductQueryService, ExternalSiteSellerRelationFinder externalSiteSellerRelationFinder, ExternalProductCommandService externalProductCommandService, ExternalPolicyContextFinder externalPolicyContextFinder) {
        this.productGroupNameCommandService = productGroupNameCommandService;
        this.externalProductQueryService = externalProductQueryService;
        this.externalSiteSellerRelationFinder = externalSiteSellerRelationFinder;
        this.externalProductCommandService = externalProductCommandService;
        this.externalPolicyContextFinder = externalPolicyContextFinder;
    }


    @Transactional
    public long syncExternalProducts(ExternalSiteSellerRelationCommand command) {
        List<Long> validSiteIds = getValidSiteIds(command);
        if (validSiteIds.isEmpty()) {
            return 0L;
        }

        List<Long> unlinkedProductGroupIds = externalProductQueryService.findUnlinkedProductGroupIds(
                command.sellerId(), command.siteIds());

        if (unlinkedProductGroupIds.isEmpty()) {
            return 0L;
        }

        Map<Long, ExternalPolicy> sitePolicyMap = toSiteIdMap(command.siteIds());
        List<ExternalProductEntity> externalProductEntities = generateExternalProductEntities(
                command.siteIds(), unlinkedProductGroupIds, sitePolicyMap);

        externalProductCommandService.saveAllExternalProduct(externalProductEntities);
        productGroupNameCommandService.insertAll(command.siteIds(), unlinkedProductGroupIds);

        return externalProductEntities.size();
    }

    private List<Long> getValidSiteIds(ExternalSiteSellerRelationCommand command) {
        List<Long> existingPolicyIds = externalSiteSellerRelationFinder.findExternalSiteSellerRelation(
                        List.of(command.sellerId()))
                .stream()
                .flatMap(relation -> relation.externalSiteProductPolicies().stream())
                .map(ExternalSiteProductPolicy::siteId)
                .toList();

        return command.siteIds().stream()
                .filter(existingPolicyIds::contains)
                .toList();
    }


    private Map<Long, ExternalPolicy> toSiteIdMap(List<Long> siteIds) {
        return externalPolicyContextFinder.findExternalPolicies(siteIds).stream()
                .collect(Collectors.toMap(ExternalPolicy::siteId, Function.identity()));
    }

    private List<ExternalProductEntity> generateExternalProductEntities(
            List<Long> siteIds,
            List<Long> productGroupIds,
            Map<Long, ExternalPolicy> sitePolicyMap) {

        return siteIds.stream()
                .flatMap(siteId -> productGroupIds.stream()
                        .map(productGroupId -> {
                            ExternalPolicy policy = sitePolicyMap.get(siteId);
                            if (policy != null) {
                                return createExternalProductEntity(siteId, productGroupId, policy.policyId());
                            }
                            return null;
                        }))
                .filter(Objects::nonNull)
                .toList();
    }

    private ExternalProductEntity createExternalProductEntity(long siteId, long productGroupId, long policyId) {
        return new ExternalProductEntity(siteId, productGroupId, policyId);
    }


}
