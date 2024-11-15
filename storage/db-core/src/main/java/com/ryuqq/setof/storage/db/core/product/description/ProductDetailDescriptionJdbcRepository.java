package com.ryuqq.setof.storage.db.core.product.description;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductDetailDescriptionJdbcRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProductDetailDescriptionJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public int[] batchInsertProductDetailDescriptions(List<ProductDetailDescriptionEntity> productDetailDescriptionEntities) {

        String sql = "INSERT PRODUCT_GROUP_DETAIL_DESCRIPTION(PRODUCT_GROUP_ID, DETAIL_DESCRIPTION )" +
                "VALUES(:productGroupId, :detailDescription)";

        List<Map<String, Object>> batchValues = productDetailDescriptionEntities.stream()
                .map(description -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("productGroupId", description.getProductGroupId())
                            .addValue("detailDescription", description.getDetailDescription());


                    return params.getValues();
        }).toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }

    public int[] batchUpdateProductDetailDescriptions(List<ProductDetailDescriptionEntity> productDetailDescriptionEntities) {
        String sql = "UPDATE PRODUCT_GROUP_DETAIL_DESCRIPTION " +
                "SET PRODUCT_GROUP_ID = :productGroupId," +
                " DETAIL_DESCRIPTION = :detailDescription";

        List<Map<String, Object>> batchValues = productDetailDescriptionEntities.stream()
                .map(description -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("productGroupId", description.getProductGroupId())
                            .addValue("detailDescription", description.getDetailDescription());
                    return params.getValues();
                }).toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }
}
