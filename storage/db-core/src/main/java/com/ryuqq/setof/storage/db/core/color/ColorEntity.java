package com.ryuqq.setof.storage.db.core.color;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "COLOR")
@Entity
public class ColorEntity extends BaseEntity {

    @Column(name = "COLOR_NAME", nullable = false, length = 50)
    private String colorName;

    protected ColorEntity() {}

    public ColorEntity(String colorName) {
        this.colorName = colorName;
    }

    public String getColorName() {
        return colorName;
    }


}
