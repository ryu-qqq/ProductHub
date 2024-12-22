package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.enums.core.OptionName;
import com.ryuqq.setof.db.core.product.option.detail.OptionDetailEntity;
import com.ryuqq.setof.db.core.product.option.group.OptionGroupEntity;

public record OptionCommand(
        OptionName name,
        String value
) {

    public OptionGroupEntity toGroupEntity(){
        return new OptionGroupEntity(name);
    }

    public OptionDetailEntity toDetailEntity(long optionGroupId){
        return new OptionDetailEntity(optionGroupId, value);
    }

}
