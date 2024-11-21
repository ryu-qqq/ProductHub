package com.ryuqq.setof.storage.db.core.site;

import java.util.List;

public interface ExternalPricePolicyPersistenceService {

    long insert(ExternalPricePolicyEntity externalPricePolicyEntity);

    void insertPolicyRules(List<ExternalPricePolicyRuleEntity> pricePolicyRuleEntities);
}
