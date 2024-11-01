package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "CRAWL_MAPPING")
@Entity
public class CrawlMappingEntity extends BaseEntity {

    private long crawlSettingId;
    private long authSettingId;

    protected CrawlMappingEntity() {}

    public CrawlMappingEntity(long crawlSettingId, long authSettingId) {
        this.crawlSettingId = crawlSettingId;
        this.authSettingId = authSettingId;
    }

}
