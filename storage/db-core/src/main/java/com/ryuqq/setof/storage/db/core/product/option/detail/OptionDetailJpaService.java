package com.ryuqq.setof.storage.db.core.product.option.detail;

import org.springframework.stereotype.Service;

@Service
public class OptionDetailJpaService implements OptionDetailPersistenceService {

    private final OptionDetailJpaRepository optionDetailJpaRepository;

    public OptionDetailJpaService(OptionDetailJpaRepository optionDetailJpaRepository) {
        this.optionDetailJpaRepository = optionDetailJpaRepository;
    }

    @Override
    public long insert(OptionDetailEntity optionDetailEntity) {
        return optionDetailJpaRepository.save(optionDetailEntity).getId();
    }

}
