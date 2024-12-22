package com.ryuqq.setof.storage.db.core.product.option.detail;

import java.util.List;

public interface OptionDetailPersistenceService {

    long insert(OptionDetailEntity optionDetailEntity);

    void deleteAll(List<Long> optionDetailIds);
}
