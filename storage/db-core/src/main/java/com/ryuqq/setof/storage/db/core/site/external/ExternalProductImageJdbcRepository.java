package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.storage.db.core.product.image.ProductGroupImageEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ExternalProductImageJdbcRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ExternalProductImageJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int[] batchInsertExternalProductImages(List<ExternalProductImageEntity> externalProductImageEntities) {
        String sql = "INSERT INTO EXTERNAL_PRODUCT_IMAGE " +
                "(EXTERNAL_PRODUCT_ID, DISPLAY_ORDER, IMAGE_URL, ORIGIN_URL) " +
                "VALUES (:externalProductId, :displayOrder, :imageUrl, :originUrl)";

        List<Map<String, Object>> batchValues = externalProductImageEntities.stream()
                .map(image -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("externalProductId", image.getExternalProductId())
                            .addValue("displayOrder", image.getDisplayOrder())
                            .addValue("imageUrl", image.getImageUrl())
                            .addValue("originUrl", image.getOriginUrl());
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }

    public int[] batchUpdateExternalProductImages(List<ExternalProductImageEntity> externalProductImageEntities) {
        String sql = "UPDATE EXTERNAL_PRODUCT_IMAGE " +
                "SET IMAGE_URL = :imageUrl, " +
                "ORIGIN_URL = :originUrl, " +
                "DISPLAY_ORDER = :displayOrder, " +
                "DELETE_YN = :deleteYn " +
                "WHERE ID = :id";

        List<Map<String, Object>> batchValues = externalProductImageEntities.stream()
                .map(image -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("imageUrl", image.getImageUrl())
                            .addValue("originUrl", image.getOriginUrl())
                            .addValue("displayOrder", image.getDisplayOrder())
                            .addValue("deleteYn", image.isDeleteYn())
                            .addValue("id", image.getId());
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }

}
