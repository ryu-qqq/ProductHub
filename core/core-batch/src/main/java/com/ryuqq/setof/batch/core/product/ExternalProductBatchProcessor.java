package com.ryuqq.setof.batch.core.product;

import com.ryuqq.setof.domain.core.product.ProductGroupConfig;
import com.ryuqq.setof.domain.core.site.external.ExternalSiteProductPolicy;
import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupNameConfigEntity;
import com.ryuqq.setof.storage.db.core.site.external.ExternalProductEntity;
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

    private void addExternalProduct(ExternalProductBatchInsertData result, ProductGroupConfig productGroupConfig, ExternalSiteProductPolicy policy) {
        result.addExternalProductEntity(new ExternalProductEntity(
                policy.siteId(),
                productGroupConfig.getProductGroupId(),
                policy.policyId()
        ));
    }

    private void addNameConfig(ExternalProductBatchInsertData result, ProductGroupConfig productGroupConfig, ExternalSiteProductPolicy policy) {
        result.addProductGroupNameConfigEntity(new ProductGroupNameConfigEntity(
                productGroupConfig.getConfigId(),
                policy.countryCode(),
                "",
                false
        ));
    }

    private boolean shouldAddNameConfig(ExternalSiteProductPolicy policy) {
        return policy.translated() || policy.countryCode().equals(Origin.KR);
    }

}
