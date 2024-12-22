package com.ryuqq.setof.storage.db.core.product.group;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductColorJdbcRepository implements ProductColorPersistenceRepository{


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProductColorJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void saveAllProductColorEntities(List<ProductColorEntity> productColorEntities) {
        batchInsertProductColors(productColorEntities);
    }

    public int[] batchInsertProductColors(List<ProductColorEntity> productColorEntities) {
        String sql = "INSERT INTO PRODUCT_COLOR " +
                "(PRODUCT_GROUP_ID, COLOR_ID, COLOR_NAME, PERCENTAGE ) " +
                "VALUES (:productGroupId, :colorId, :colorName, :percentage)";

        List<Map<String, Object>> batchValues = productColorEntities.stream()
                .map(color -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("productGroupId", color.getProductGroupId())
                            .addValue("colorId", color.getColorId())
                            .addValue("colorName", color.getColorName())
                            .addValue("percentage", color.getPercentage());
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }



}
