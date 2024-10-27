package com.ryuqq.setof.domain.core.product.command;

import com.ryuqq.setof.storage.db.core.product.option.group.OptionGroupPersistenceService;
import org.springframework.stereotype.Service;

@Service
public class OptionGroupCommandService {

    private final OptionGroupPersistenceService optionGroupPersistenceService;

    public OptionGroupCommandService(OptionGroupPersistenceService optionGroupPersistenceService) {
        this.optionGroupPersistenceService = optionGroupPersistenceService;
    }

    public long insert(OptionCommand optionCommand){
        return optionGroupPersistenceService.insert(optionCommand.toGroupEntity());
    }
}
