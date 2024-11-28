package com.ryuqq.setof.storage.db.core.site.external;

import java.util.List;

public interface ExternalPricePolicyPersistenceRepository {

    long saveExternalPricePolicy(ExternalPricePolicyEntity externalPricePolicyEntity);

    void saveAllExternalPricePolicyRule(List<ExternalPricePolicyRuleEntity> pricePolicyRuleEntities);
}
