package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.enums.core.SizeOrigin;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.*;

@Table(name = "SIZE_REGION")
@Entity
public class SizeRegionEntity extends BaseEntity {

    @Column(name = "NAME", nullable = false, length = 50, unique = true)
    @Enumerated(EnumType.STRING)
    private SizeOrigin name;

    public SizeRegionEntity() {}

    public SizeRegionEntity(SizeOrigin name) {
        this.name = name;
    }

}
