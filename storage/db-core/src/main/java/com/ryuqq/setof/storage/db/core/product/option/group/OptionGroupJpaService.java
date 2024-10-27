package com.ryuqq.setof.storage.db.core.product.option.group;

import org.springframework.stereotype.Service;

@Service
public class OptionGroupJpaService implements OptionGroupPersistenceService{

    private final OptionGroupJpaRepository optionGroupJpaRepository;

    public OptionGroupJpaService(OptionGroupJpaRepository optionGroupJpaRepository) {
        this.optionGroupJpaRepository = optionGroupJpaRepository;
    }

    @Override
    public long insert(OptionGroupEntity optionGroupEntity) {
        return optionGroupJpaRepository.save(optionGroupEntity).getId();
    }
}
