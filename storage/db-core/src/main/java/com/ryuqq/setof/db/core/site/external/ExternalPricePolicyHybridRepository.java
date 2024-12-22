package com.ryuqq.setof.storage.db.core.site.external;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalPricePolicyHybridRepository implements ExternalPricePolicyPersistenceRepository {

    private final ExternalPricePolicyJpaRepository externalPricePolicyJpaRepository;
    private final ExternalPricePolicyRuleJpaRepository externalPricePolicyRuleJpaRepository;

    public ExternalPricePolicyHybridRepository(ExternalPricePolicyJpaRepository externalPricePolicyJpaRepository, ExternalPricePolicyRuleJpaRepository externalPricePolicyRuleJpaRepository) {
        this.externalPricePolicyJpaRepository = externalPricePolicyJpaRepository;
        this.externalPricePolicyRuleJpaRepository = externalPricePolicyRuleJpaRepository;
    }

    @Override
    public long saveExternalPricePolicy(ExternalPricePolicyEntity externalPricePolicyEntity) {
        return externalPricePolicyJpaRepository.save(externalPricePolicyEntity).getId();
    }

    @Override
    public void saveAllExternalPricePolicyRule(List<ExternalPricePolicyRuleEntity> pricePolicyRuleEntities) {
        externalPricePolicyRuleJpaRepository.saveAll(pricePolicyRuleEntities);
    }

}
