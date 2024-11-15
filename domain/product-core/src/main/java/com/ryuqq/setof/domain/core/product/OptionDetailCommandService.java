package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.core.product.option.detail.OptionDetailPersistenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionDetailCommandService {

    private final OptionDetailPersistenceService optionDetailPersistenceService;

    public OptionDetailCommandService(OptionDetailPersistenceService optionDetailPersistenceService) {
        this.optionDetailPersistenceService = optionDetailPersistenceService;
    }

    public long insert(long optionGroupId, OptionCommand optionCommand) {
        return optionDetailPersistenceService.insert(optionCommand.toDetailEntity(optionGroupId));
    }

    public void deleteAll(List<Long> optionDetailIds){
        optionDetailPersistenceService.deleteAll(optionDetailIds);
    }

}
