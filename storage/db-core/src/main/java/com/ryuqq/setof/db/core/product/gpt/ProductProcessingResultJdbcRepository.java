package com.ryuqq.setof.storage.db.core.product.gpt;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductProcessingResultJdbcRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProductProcessingResultJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int[] batchInsertExternalProcessingResults(List<ProductProcessingResultEntity> externalProcessingResultEntities) {

        String sql = "INSERT INTO PRODUCT_PROCESSING_RESULT " +
                "(PRODUCT_GROUP_ID, PRODUCT_DATA_TYPE, RESULT) " +
                "VALUES (:productGroupId, :productDataType, :result)";

        List<Map<String, Object>> batchValues = externalProcessingResultEntities.stream()
                .map(externalProcessingResult -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("productGroupId", externalProcessingResult.getProductGroupId())
                            .addValue("productDataType", externalProcessingResult.getProductDataType().name())
                            .addValue("result", externalProcessingResult.getResult());
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }



}
