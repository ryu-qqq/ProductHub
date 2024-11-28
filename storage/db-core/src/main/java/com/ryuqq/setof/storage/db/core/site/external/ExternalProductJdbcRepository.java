package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.enums.core.SyncStatus;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ExternalProductJdbcRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ExternalProductJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int[] batchInsertExternalProducts(List<ExternalProductEntity> externalProductEntities) {

        String sql = "INSERT INTO EXTERNAL_PRODUCT " +
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

    public void updatesSyncStatus(List<Long> productGroupIds, SyncStatus status){
        String sql = "UPDATE EXTERNAL_PRODUCT " +
                "SET STATUS = :status " +
                "WHERE PRODUCT_GROUP_ID = :productGroupId";

        List<Map<String, Object>> batchValues = productGroupIds.stream()
                .map(aLong -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("status", status.name())
                            .addValue("productGroupId", aLong);
                    return params.getValues();
                })
                .toList();

        namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }




}
