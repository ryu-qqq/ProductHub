package com.ryuqq.setof.storage.db.core.product.option.group;

import com.ryuqq.setof.enums.core.OptionName;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.*;

@Table(name = "OPTION_GROUP")
@Entity
public class OptionGroupEntity extends BaseEntity {

    @Column(name = "OPTION_NAME", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private OptionName optionName;

    protected OptionGroupEntity() {}


    public OptionGroupEntity(OptionName optionName) {
        this.optionName = optionName;
    }

    public OptionGroupEntity(long id, OptionName optionName, boolean deleteYn) {
        this.id = id;
        this.optionName = optionName;
        this.deleteYn = deleteYn;
    }

    public OptionName getOptionName() {
        return optionName;
    }

}
