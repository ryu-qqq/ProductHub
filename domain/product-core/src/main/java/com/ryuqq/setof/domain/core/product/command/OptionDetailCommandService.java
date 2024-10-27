package com.ryuqq.setof.domain.core.product.command;

import com.ryuqq.setof.storage.db.core.product.option.detail.OptionDetailPersistenceService;
import org.springframework.stereotype.Service;

@Service
public class OptionDetailCommandService {

    private final OptionDetailPersistenceService optionDetailPersistenceService;

    public OptionDetailCommandService(OptionDetailPersistenceService optionDetailPersistenceService) {
        this.optionDetailPersistenceService = optionDetailPersistenceService;
    }

    public long insert(long optionGroupId, OptionCommand optionCommand) {
        return optionDetailPersistenceService.insert(optionCommand.toDetailEntity(optionGroupId));
    }
}
