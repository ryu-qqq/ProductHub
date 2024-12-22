package com.ryuqq.setof.storage.db.core.site.crawl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CrawlProductJdbcRepository implements CrawlProductPersistenceService{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CrawlProductJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void batchInsertCrawlProducts(List<CrawlProductEntity> crawlProductEntities) {
        String sql = "INSERT INTO CRAWL_PRODUCT (SITE_ID, SITE_PRODUCT_ID, PRODUCT_GROUP_ID, PRODUCT_NAME) " +
                "VALUES (:siteId, :siteProductId, :productGroupId, :productName) " +
                "ON DUPLICATE KEY UPDATE " +
                "PRODUCT_GROUP_ID = VALUES(PRODUCT_GROUP_ID), " +
                "PRODUCT_NAME = VALUES(PRODUCT_NAME) ";

        SqlParameterSource[] batchParams = SqlParameterSourceUtils.createBatch(crawlProductEntities.toArray());

        namedParameterJdbcTemplate.batchUpdate(sql, batchParams);
    }
}
