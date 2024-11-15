package com.ryuqq.setof.storage.db.core.product.group;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductGroupJdbcRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProductGroupJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    // 1. 배치 인서트 메서드
    public int[] batchInsertProductGroups(List<ProductGroupEntity> productGroups) {
        String sql = "INSERT INTO PRODUCT_GROUP " +
                "(SELLER_ID, CATEGORY_ID, BRAND_ID, PRODUCT_GROUP_NAME, STYLE_CODE, PRODUCT_CONDITION, " +
                "MANAGEMENT_TYPE, OPTION_TYPE, REGULAR_PRICE, CURRENT_PRICE, DISCOUNT_RATE, SOLD_OUT_YN, " +
                "DISPLAY_YN, PRODUCT_STATUS, KEYWORDS) " +
                "VALUES (:sellerId, :categoryId, :brandId, :productGroupName, :styleCode, :productCondition, " +
                ":managementType, :optionType, :regularPrice, :currentPrice, :discountRate, :soldOutYn, " +
                ":displayYn, :productStatus, :keywords)";

        List<Map<String, Object>> batchValues = productGroups.stream()
                .map(group -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("sellerId", group.getSellerId())
                            .addValue("categoryId", group.getCategoryId())
                            .addValue("brandId", group.getBrandId())
                            .addValue("productGroupName", group.getProductGroupName())
                            .addValue("styleCode", group.getStyleCode())
                            .addValue("productCondition", group.getProductCondition().toString())
                            .addValue("managementType", group.getManagementType().toString())
                            .addValue("optionType", group.getOptionType().toString())
                            .addValue("regularPrice", group.getRegularPrice())
                            .addValue("currentPrice", group.getCurrentPrice())
                            .addValue("discountRate", group.getDiscountRate())
                            .addValue("soldOutYn", group.isSoldOutYn())
                            .addValue("displayYn", group.isDisplayYn())
                            .addValue("productStatus", group.getProductStatus().toString())
                            .addValue("keywords", group.getKeywords());

                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }

    // 2. 배치 업데이트 메서드
    public int[] batchUpdateProductGroups(List<ProductGroupEntity> productGroups) {
        String sql = "UPDATE PRODUCT_GROUP " +
                "SET CATEGORY_ID = :categoryId, " +
                "BRAND_ID = :brandId, " +
                "PRODUCT_GROUP_NAME = :productGroupName, " +
                "STYLE_CODE = :styleCode, " +
                "PRODUCT_CONDITION = :productCondition, " +
                "MANAGEMENT_TYPE = :managementType, " +
                "OPTION_TYPE = :optionType, " +
                "REGULAR_PRICE = :regularPrice, " +
                "CURRENT_PRICE = :currentPrice, " +
                "DISCOUNT_RATE = :discountRate, " +
                "SOLD_OUT_YN = :soldOutYn, " +
                "DISPLAY_YN = :displayYn, " +
                "PRODUCT_STATUS = :productStatus, " +
                "KEYWORDS = :keywords " +
                "WHERE SELLER_ID = :sellerId";

        List<Map<String, Object>> batchValues = productGroups.stream()
                .map(group -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("sellerId", group.getSellerId())
                            .addValue("categoryId", group.getCategoryId())
                            .addValue("brandId", group.getBrandId())
                            .addValue("productGroupName", group.getProductGroupName())
                            .addValue("styleCode", group.getStyleCode())
                            .addValue("productCondition", group.getProductCondition().toString())
                            .addValue("managementType", group.getManagementType().toString())
                            .addValue("optionType", group.getOptionType().toString())
                            .addValue("regularPrice", group.getRegularPrice())
                            .addValue("currentPrice", group.getCurrentPrice())
                            .addValue("discountRate", group.getDiscountRate())
                            .addValue("soldOutYn", group.isSoldOutYn())
                            .addValue("displayYn", group.isDisplayYn())
                            .addValue("productStatus", group.getProductStatus().toString())
                            .addValue("keywords", group.getKeywords());
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }
}
