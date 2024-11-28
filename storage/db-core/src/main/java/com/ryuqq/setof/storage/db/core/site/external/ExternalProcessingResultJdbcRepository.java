package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.enums.core.SyncStatus;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ExternalProcessingResultJdbcRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ExternalProcessingResultJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int[] batchInsertExternalProcessingResults(List<ExternalProcessingResultEntity> externalProcessingResultEntities) {

        String sql = "INSERT INTO EXTERNAL_PROCESSING_RESULT " +
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
