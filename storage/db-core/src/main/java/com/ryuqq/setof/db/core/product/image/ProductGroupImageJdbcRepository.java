package com.ryuqq.setof.storage.db.core.product.image;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductGroupImageJdbcRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProductGroupImageJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int[] batchInsertProductGroupImages(List<ProductGroupImageEntity> productGroupImageEntities) {
        String sql = "INSERT INTO PRODUCT_GROUP_IMAGE " +
                "(PRODUCT_GROUP_ID, PRODUCT_GROUP_IMAGE_TYPE, IMAGE_URL, ORIGIN_URL) " +
                "VALUES (:productGroupId, :productGroupImageType, :imageUrl, :originUrl)";

        List<Map<String, Object>> batchValues = productGroupImageEntities.stream()
                .map(image -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("productGroupId", image.getProductGroupId())
                            .addValue("productGroupImageType", image.getProductImageType().toString())
                            .addValue("imageUrl", image.getImageUrl())
                            .addValue("originUrl", image.getOriginUrl());
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }

    public int[] batchUpdateProductGroups(List<ProductGroupImageEntity> productGroupImageEntities) {
        String sql = "UPDATE PRODUCT_GROUP_IMAGE " +
                "SET IMAGE_URL = :imageUrl, " +
                "ORIGIN_URL = :originUrl, " +
                "PRODUCT_GROUP_IMAGE_TYPE = :productGroupImageType, " +
                "DELETE_YN = :deleteYn " +
                "WHERE ID = :id";

        List<Map<String, Object>> batchValues = productGroupImageEntities.stream()
                .map(image -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("imageUrl", image.getImageUrl())
                            .addValue("originUrl", image.getOriginUrl())
                            .addValue("productGroupImageType", image.getProductImageType().name())
                            .addValue("deleteYn", image.isDeleteYn())
                            .addValue("id", image.getId());
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }

}
