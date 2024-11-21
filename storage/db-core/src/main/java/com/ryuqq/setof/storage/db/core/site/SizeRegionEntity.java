package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "SIZE_REGION")
@Entity
public class SizeRegionEntity extends BaseEntity {

    @Column(name = "NAME", nullable = false, length = 50, unique = true)
    private String name;

    public SizeRegionEntity() {}

    public SizeRegionEntity(String name) {
        this.name = name;
    }
}
