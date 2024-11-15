package com.ryuqq.setof.storage.db.core.product.option.detail;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "OPTION_DETAIL")
@Entity
public class OptionDetailEntity extends BaseEntity {

    @Column(name = "OPTION_GROUP_ID", nullable = false)
    private long optionGroupId;

    @Column(name = "OPTION_VALUE", length = 50, nullable = false)
    private String optionValue;

    protected OptionDetailEntity() {}

    public OptionDetailEntity(long optionGroupId, String optionValue) {
        this.optionGroupId = optionGroupId;
        this.optionValue = optionValue;
    }

    public OptionDetailEntity(long id, long optionGroupId, String optionValue, boolean deleteYn) {
        this.id =id;
        this.optionGroupId = optionGroupId;
        this.optionValue = optionValue;
        this.deleteYn = deleteYn;
    }

}

