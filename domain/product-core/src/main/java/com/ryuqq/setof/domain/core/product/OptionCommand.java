package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.core.OptionName;
import com.ryuqq.setof.storage.db.core.product.option.detail.OptionDetailEntity;
import com.ryuqq.setof.storage.db.core.product.option.group.OptionGroupEntity;

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

    public Option toOption() {
        return new Option(
                null,
                null,
                null,
                name,
                value
        );
    }

}
