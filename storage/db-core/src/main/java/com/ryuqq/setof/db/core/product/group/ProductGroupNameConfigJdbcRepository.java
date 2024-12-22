package com.ryuqq.setof.storage.db.core.product.group;

import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupNameConfigDto;
import com.ryuqq.setof.storage.db.core.product.dto.ProductStyleCodeDto;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductGroupNameConfigJdbcRepository {


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProductGroupNameConfigJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int[] batchInsertProductConfigs(List<ProductGroupNameConfigEntity> productGroupNameConfigEntities) {
        String sql = "INSERT INTO PRODUCT_GROUP_NAME_CONFIG " +
                "(PRODUCT_GROUP_CONFIG_ID, COUNTRY_CODE, DEFAULT_YN) " +
                "VALUES (:productGroupConfigId, :countryCode, :defaultYn)";

        List<Map<String, Object>> batchValues = productGroupNameConfigEntities.stream()
                .map(config -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("productGroupConfigId", config.getProductGroupConfigId())
                            .addValue("countryCode", config.getCountryCode().name())
                            .addValue("defaultYn", config.isDefaultYn());
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }


    public void batchUpdateProductGroupNameConfigs(List<ProductGroupNameConfigDto> productGroupNameConfigs){
        String sql = "UPDATE PRODUCT_GROUP_NAME_CONFIG " +
                "SET CUSTOM_NAME = :customName " +
                "WHERE ID = :productGroupNameConfigId";

        List<Map<String, Object>> batchValues = productGroupNameConfigs.stream()
                .map(config -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("customName", config.getCustomName())
                            .addValue("productGroupNameConfigId", config.getProductGroupNameConfigId());
                    return params.getValues();
                })
                .toList();
        namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));

    }

}
