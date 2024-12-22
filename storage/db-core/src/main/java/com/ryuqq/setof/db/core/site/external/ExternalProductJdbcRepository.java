package com.ryuqq.setof.storage.db.core.site.external;

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
                "(EXTERNAL_PRODUCT_GROUP_ID, EXTERNAL_PRODUCT_ID, OPTION_VALUE, QUANTITY, ADDITIONAL_PRICE, SOLD_OUT_YN, DISPLAY_YN) " +
                "VALUES (:externalProductGroupId, :externalProductId, :optionValue, :quantity, :additionalPrice, :soldOutYn, :displayYn)";

        List<Map<String, Object>> batchValues = externalProductEntities.stream()
                .map(externalProduct -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("externalProductGroupId", externalProduct.getExternalProductGroupId())
                            .addValue("externalProductId", externalProduct.getExternalProductId())
                            .addValue("optionValue", externalProduct.getOptionValue())
                            .addValue("quantity", externalProduct.getQuantity())
                            .addValue("additionalPrice", externalProduct.getAdditionalPrice())
                            .addValue("soldOutYn", externalProduct.isSoldOutYn())
                            .addValue("displayYn", externalProduct.isDisplayYn());
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }


    public int[] deleteAll(List<String> externalProductGroupIds) {
        String sql = "UPDATE EXTERNAL_PRODUCT " +
                "SET DELETE_YN = :deleteYn " +
                "WHERE EXTERNAL_PRODUCT_GROUP_ID = :externalProductGroupId AND DELETE_YN = 0";

        List<Map<String, Object>> batchValues = externalProductGroupIds.stream()
                .map(aLong -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("deleteYn", true)
                            .addValue("externalProductGroupId", aLong);
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }

}
