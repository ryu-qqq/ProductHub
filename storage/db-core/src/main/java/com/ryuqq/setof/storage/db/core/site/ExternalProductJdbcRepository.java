package com.ryuqq.setof.storage.db.core.site;

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


}
