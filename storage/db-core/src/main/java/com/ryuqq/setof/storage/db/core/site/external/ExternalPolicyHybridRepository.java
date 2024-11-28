package com.ryuqq.setof.storage.db.core.site.external;

import org.springframework.stereotype.Service;



@Service
public class ExternalPolicyHybridRepository implements ExternalPolicyPersistenceRepository {

    private final ExternalPolicyJpaRepository externalPolicyJpaRepository;

    public ExternalPolicyHybridRepository(ExternalPolicyJpaRepository externalPolicyJpaRepository) {
        this.externalPolicyJpaRepository = externalPolicyJpaRepository;
    }

    @Override
    public long insert(ExternalPolicyEntity externalPolicyEntity) {
        return externalPolicyJpaRepository.save(externalPolicyEntity).getId();
    }

}
