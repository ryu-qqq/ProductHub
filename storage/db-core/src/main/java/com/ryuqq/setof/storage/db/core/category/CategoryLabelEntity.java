package com.ryuqq.setof.storage.db.core.category;

import com.ryuqq.setof.core.LanguageCode;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "CATEGORY_LABEL")
@Entity
public class CategoryLabelEntity extends BaseEntity {

    @Column(name = "CATEGORY_ID", nullable = false)
    private long categoryId;

    @Column(name = "LANGUAGE_CODE", length = 5, nullable = false)
    private LanguageCode languageCode;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

}
