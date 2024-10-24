package com.ryuqq.setof.storage.db.core.product;

import com.ryuqq.setof.core.OptionName;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.*;

@Table(name = "OPTION_GROUP")
@Entity
public class OptionGroupEntity extends BaseEntity {

    @Column(name = "OPTION_NAME", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private OptionName optionName;

    protected OptionGroupEntity() {}

    public OptionGroupEntity(OptionName optionName) {
        this.optionName = optionName;
    }

    public OptionName getOptionName() {
        return optionName;
    }
}
