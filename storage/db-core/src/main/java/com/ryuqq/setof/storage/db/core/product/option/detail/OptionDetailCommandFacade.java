package com.ryuqq.setof.storage.db.core.product.option.detail;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OptionDetailCommandFacade implements OptionDetailPersistenceService {

    private final OptionDetailJpaRepository optionDetailJpaRepository;
    private final OptionDetailJdbcRepository optionDetailJdbcRepository;

    public OptionDetailCommandFacade(OptionDetailJpaRepository optionDetailJpaRepository, OptionDetailJdbcRepository optionDetailJdbcRepository) {
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
