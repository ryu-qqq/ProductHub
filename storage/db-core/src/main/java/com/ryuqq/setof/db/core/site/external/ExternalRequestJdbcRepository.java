package com.ryuqq.setof.storage.db.core.site.external;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ExternalRequestJdbcRepository implements ExternalRequestPersistenceRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ExternalRequestJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int[] batchInsertExternalProcessingResults(List<ExternalRequestEntity> externalRequestEntities) {

        String sql = "INSERT INTO EXTERNAL_REQUEST " +
                "(TRANSACTION_ID, REQUEST_TYPE, SITE_ID, ENTITY_TYPE, ENTITY_ID, STATUS_VALUE, STATUS_MESSAGE, STATUS, REQUEST_BODY) " +
                "VALUES (:transactionId, :requestType, :siteId, :entityType, :entityId, :statusValue, :statusMessage, :status, :requestBody)";

        List<Map<String, Object>> batchValues = externalRequestEntities.stream()
                .map(request -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("transactionId", request.getTransactionId())
                            .addValue("requestType", request.getRequestType().name())
                            .addValue("siteId", request.getSiteId())
                            .addValue("entityType", request.getEntityType().name())
                            .addValue("entityId", request.getEntityId())
                            .addValue("statusValue", request.getStatusValue())
                            .addValue("statusMessage", request.getStatusMessage())
                            .addValue("status", request.getStatus().name())
                            .addValue("requestBody", request.getRequestBody());
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }

    @Override
    public void saveAllExternalRequest(List<ExternalRequestEntity> externalRequestEntities) {
        batchInsertExternalProcessingResults(externalRequestEntities);
    }
}
