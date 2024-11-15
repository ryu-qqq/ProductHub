package com.ryuqq.setof.storage.db.core.product.option;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductJdbcRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProductJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public int[] batchUpdateProducts(List<ProductEntity> productEntities) {
        String sql = "UPDATE PRODUCT " +
                "SET SOLD_OUT_YN = :soldOutYn, " +
                " DISPLAY_YN = :displayYn, " +
                " QUANTITY = :quantity, " +
                " ADDITIONAL_PRICE = :additionalPrice, " +
                " DELETE_YN = :deleteYn, " +

                " WHERE PRODUCT_ID = :productId";

        List<Map<String, Object>> batchValues = productEntities.stream()
                .map(product -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("soldOutYn", product.isSoldOutYn())
                            .addValue("displayYn", product.isDisplayYn())
                            .addValue("quantity", product.getQuantity())
                            .addValue("additionalPrice", product.getAdditionalPrice())
                            .addValue("deleteYn", product.isDeleteYn())
                            ;
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }
}
