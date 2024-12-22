package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.enums.core.SyncStatus;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class ExternalProductGroupJdbcRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ExternalProductGroupJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int[] batchInsertExternalProductGroups(List<ExternalProductGroupEntity> externalProductEntities) {

        String sql = "INSERT INTO EXTERNAL_PRODUCT_GROUP " +
                "(SITE_ID, PRODUCT_GROUP_ID, POLICY_ID, STATUS) " +
                "VALUES (:siteId, :productGroupId, :policyId, :status)";

        List<Map<String, Object>> batchValues = externalProductEntities.stream()
                .map(externalProduct -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("siteId", externalProduct.getSiteId())
                            .addValue("productGroupId", externalProduct.getProductGroupId())
                            .addValue("policyId", externalProduct.getPolicyId())
                            .addValue("status", externalProduct.getStatus().name());
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }


    public int[] batchUpdateExternalProductGroups(List<ExternalProductGroupEntity> externalProductEntities) {
        String sql = "UPDATE EXTERNAL_PRODUCT_GROUP " +
                "SET EXTERNAL_PRODUCT_GROUP_ID = CASE WHEN :externalProductGroupId IS NOT NULL AND :externalProductGroupId != '' THEN :externalProductGroupId ELSE EXTERNAL_PRODUCT_GROUP_ID END, " +
                "PRODUCT_NAME = :productName, " +
                "REGULAR_PRICE = :regularPrice, " +
                "CURRENT_PRICE = :currentPrice, " +
                "STATUS = :status, " +
                "FIXED_PRICE_YN = :fixedPriceYn, " +
                "SOLD_OUT_YN = :soldOutYn, " +
                "DISPLAY_YN = :displayYn, " +
                "UPDATE_TIME = :lastSyncTime " +
                "WHERE SITE_ID = :siteId AND PRODUCT_GROUP_ID = :productGroupId";

        List<Map<String, Object>> batchValues = externalProductEntities.stream()
                .map(item -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("externalProductGroupId", item.getExternalProductGroupId())
                            .addValue("productName", item.getProductName())
                            .addValue("regularPrice", item.getRegularPrice())
                            .addValue("currentPrice", item.getCurrentPrice())
                            .addValue("status", item.getStatus().name())
                            .addValue("fixedPriceYn", item.isFixedPriceYn())
                            .addValue("soldOutYn", item.isSoldOutYn())
                            .addValue("displayYn", item.isDisplayYn())
                            .addValue("lastSyncTime", LocalDateTime.now())
                            .addValue("productGroupId", item.getProductGroupId())
                            .addValue("siteId", item.getSiteId());
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }



    public void updatesSyncStatus(List<Long> productGroupIds, Long siteId, SyncStatus status){
        String sql = "UPDATE EXTERNAL_PRODUCT_GROUP " +
                "SET STATUS = :status " +
                "WHERE PRODUCT_GROUP_ID = :productGroupId AND SITE_ID = :siteId";

        List<Map<String, Object>> batchValues = productGroupIds.stream()
                .map(aLong -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("status", status.name())
                            .addValue("siteId", siteId)
                            .addValue("productGroupId", aLong);
                    return params.getValues();
                })
                .toList();

        namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }

}
