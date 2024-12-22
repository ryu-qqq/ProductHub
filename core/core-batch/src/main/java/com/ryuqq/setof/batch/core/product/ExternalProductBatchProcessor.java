package com.ryuqq.setof.batch.core.product;

import com.ryuqq.setof.domain.core.product.ProductGroupConfig;
import com.ryuqq.setof.domain.core.site.external.ExternalProductPolicy;
import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.db.core.product.group.ProductGroupNameConfigEntity;
import com.ryuqq.setof.db.core.site.external.ExternalProductGroupEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalProductBatchProcessor implements ItemProcessor<List<ExternalProductProcessingData>, ExternalProductBatchInsertData> {

    @Override
    public ExternalProductBatchInsertData process(List<ExternalProductProcessingData> items) {
        ExternalProductBatchInsertData result = new ExternalProductBatchInsertData();

        items.forEach(data -> processData(data, result));

        return result;
    }

    private void processData(ExternalProductProcessingData data, ExternalProductBatchInsertData result) {
        ProductGroupConfig productGroupConfig = data.productGroupConfigContext().getProductGroupConfig();

        if (data.externalSiteProductPolicies().isEmpty()) {
            addDefaultNameConfig(result, productGroupConfig);
        } else {
            data.externalSiteProductPolicies().forEach(policy -> {
                addExternalProduct(result, productGroupConfig, policy);

                if (shouldAddNameConfig(policy)) {
                    addNameConfig(result, productGroupConfig, policy);
                }
            });
        }
    }

    private void addDefaultNameConfig(ExternalProductBatchInsertData result, ProductGroupConfig productGroupConfig) {
        result.addProductGroupNameConfigEntity(new ProductGroupNameConfigEntity(
                productGroupConfig.getConfigId(),
                Origin.KR,
                "",
                true
        ));
    }

    private void addExternalProduct(ExternalProductBatchInsertData result, ProductGroupConfig productGroupConfig, ExternalProductPolicy policy) {
        result.addExternalProductEntity(ExternalProductGroupEntity.toWaitingStatusEntity(policy.siteId(), productGroupConfig.getProductGroupId(), policy.policyId()));
    }

    private void addNameConfig(ExternalProductBatchInsertData result, ProductGroupConfig productGroupConfig, ExternalProductPolicy policy) {
        result.addProductGroupNameConfigEntity(ProductGroupNameConfigEntity.toInitEntity(productGroupConfig.getConfigId(), policy.countryCode()));
    }

    private boolean shouldAddNameConfig(ExternalProductPolicy policy) {
        return policy.translated() || policy.countryCode().equals(Origin.KR);
    }

}
