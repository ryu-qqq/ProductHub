package com.ryuqq.setof.storage.db.core.site.external;

import org.springframework.stereotype.Service;

@Service
public class ExternalProductPolicyHybridRepository implements ExternalProductPolicyPersistenceRepository {

    private final ExternalProductPolicyJpaRepository externalProductPolicyJpaRepository;

    public ExternalProductPolicyHybridRepository(ExternalProductPolicyJpaRepository externalProductPolicyJpaRepository) {
        this.externalProductPolicyJpaRepository = externalProductPolicyJpaRepository;
    }

    @Override
    public void insertExternalProductPolicy(ExternalProductPolicyEntity externalProductPolicyEntity) {
        externalProductPolicyJpaRepository.save(externalProductPolicyEntity);
    }

}
