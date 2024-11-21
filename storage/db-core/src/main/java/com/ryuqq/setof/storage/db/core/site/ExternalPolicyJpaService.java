package com.ryuqq.setof.storage.db.core.site;

import org.springframework.stereotype.Service;



@Service
public class ExternalPolicyJpaService implements ExternalPolicyPersistenceService{

    private final ExternalPolicyJpaRepository externalPolicyJpaRepository;

    public ExternalPolicyJpaService(ExternalPolicyJpaRepository externalPolicyJpaRepository) {
        this.externalPolicyJpaRepository = externalPolicyJpaRepository;
    }

    @Override
    public long insert(ExternalPolicyEntity externalPolicyEntity) {
        return externalPolicyJpaRepository.save(externalPolicyEntity).getId();
    }

}
