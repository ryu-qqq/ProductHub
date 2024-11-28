package com.ryuqq.setof.batch.core.product;

import com.ryuqq.setof.domain.core.product.*;
import com.ryuqq.setof.domain.core.site.external.ExternalSiteProductPolicy;
import com.ryuqq.setof.domain.core.site.external.ExternalSiteSellerRelationFinder;
import com.ryuqq.setof.domain.core.site.external.ExternalSiteSellerRelation;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ExternalProductBatchReader implements ItemReader<List<ExternalProductProcessingData>> {

    private final ProductGroupConfigContextFinder productGroupConfigContextFinder;
    private final ExternalSiteSellerRelationFinder externalSiteSellerRelationFinder;

    private boolean hasRead = false;

    public ExternalProductBatchReader(ProductGroupConfigContextFinder productGroupConfigContextFinder, ExternalSiteSellerRelationFinder externalSiteSellerRelationFinder) {
        this.productGroupConfigContextFinder = productGroupConfigContextFinder;
        this.externalSiteSellerRelationFinder = externalSiteSellerRelationFinder;
    }


    @Override
    public List<ExternalProductProcessingData> read() {
        if (hasRead) {
            return null;
        }

        StepExecution stepExecution = Objects.requireNonNull(StepSynchronizationManager.getContext()).getStepExecution();
        List<Long> productGroupIds = (List<Long>) stepExecution.getJobExecution().getExecutionContext().get("productGroupIds");
        if (productGroupIds == null || productGroupIds.isEmpty()) {
            return null;
        }

        List<Long> sellerIds = (List<Long>) stepExecution.getJobExecution().getExecutionContext().get("sellerIds");
        if (sellerIds == null || sellerIds.isEmpty()) {
            return null;
        }

        List<ProductGroupConfigContext> productGroupConfigContexts = productGroupConfigContextFinder.fetchByProductGroupIds(productGroupIds);

        Map<Long, List<ExternalSiteProductPolicy>> sellerSiteMap = externalSiteSellerRelationFinder.findExternalSiteSellerRelation(sellerIds)
                .stream()
                .collect(Collectors.toMap(
                        ExternalSiteSellerRelation::sellerId,
                        ExternalSiteSellerRelation::externalSiteProductPolicies
                ));

        hasRead = true;

        return productGroupConfigContexts.stream()
                .map(p -> {
                    List<ExternalSiteProductPolicy> externalSiteProductPolicyDtos = sellerSiteMap.getOrDefault(p.getSellerId(), List.of());
                    return new ExternalProductProcessingData(p, externalSiteProductPolicyDtos);
                })
                .toList();
    }



}
