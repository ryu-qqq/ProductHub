package com.ryuqq.setof.storage.db.core.product.option.detail;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionDetailJpaService implements OptionDetailPersistenceService {

    private final OptionDetailJpaRepository optionDetailJpaRepository;
    private final OptionDetailJdbcRepository optionDetailJdbcRepository;

    public OptionDetailJpaService(OptionDetailJpaRepository optionDetailJpaRepository, OptionDetailJdbcRepository optionDetailJdbcRepository) {
        this.optionDetailJpaRepository = optionDetailJpaRepository;
        this.optionDetailJdbcRepository = optionDetailJdbcRepository;
    }

    @Override
    public long insert(OptionDetailEntity optionDetailEntity) {
        return optionDetailJpaRepository.save(optionDetailEntity).getId();
    }

    @Override
    public void deleteAll(List<Long> optionDetailIds) {
        optionDetailJdbcRepository.softDeleteAll(optionDetailIds);
    }


}
