package com.ryuqq.setof.storage.db.core.product.group;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductGroupConfigJdbcRepository {


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProductGroupConfigJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int[] batchInsertProductConfigs(List<ProductGroupConfigEntity> productGroupConfigEntities) {
        String sql = "INSERT INTO PRODUCT_GROUP_CONFIG " +
                "(PRODUCT_GROUP_ID, ACTIVE_YN) " +
                "VALUES (:productGroupId, :activeYn)";

        List<Map<String, Object>> batchValues = productGroupConfigEntities.stream()
                .map(config -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("productGroupId", config.getProductGroupId())
                            .addValue("activeYn", config.isActiveYn());
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }

}
