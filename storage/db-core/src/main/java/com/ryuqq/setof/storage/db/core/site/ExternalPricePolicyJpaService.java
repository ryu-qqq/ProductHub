package com.ryuqq.setof.storage.db.core.site;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalPricePolicyJpaService implements ExternalPricePolicyPersistenceService {

    private final ExternalPricePolicyJpaRepository externalPricePolicyJpaRepository;
    private final ExternalPricePolicyRuleJpaRepository externalPricePolicyRuleJpaRepository;

    public ExternalPricePolicyJpaService(ExternalPricePolicyJpaRepository externalPricePolicyJpaRepository, ExternalPricePolicyRuleJpaRepository externalPricePolicyRuleJpaRepository) {
        this.externalPricePolicyJpaRepository = externalPricePolicyJpaRepository;
        this.externalPricePolicyRuleJpaRepository = externalPricePolicyRuleJpaRepository;
    }

    @Override
    public long insert(ExternalPricePolicyEntity externalPricePolicyEntity) {
        return externalPricePolicyJpaRepository.save(externalPricePolicyEntity).getId();
    }

    @Override
    public void insertPolicyRules(List<ExternalPricePolicyRuleEntity> pricePolicyRuleEntities) {
        externalPricePolicyRuleJpaRepository.saveAll(pricePolicyRuleEntities);
    }

}
