package com.ryuqq.setof.storage.db.core.product.group;

import com.ryuqq.setof.enums.core.ProductStatus;
import com.ryuqq.setof.storage.db.core.product.dto.ProductStyleCodeDto;
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
                "WHERE ID = :productGroupId";

        List<Map<String, Object>> batchValues = productGroups.stream()
                .map(group -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("productGroupId", group.getId())
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

    public void updatesProductStatus(List<Long> productGroupIds, ProductStatus productStatus){
        String sql = "UPDATE PRODUCT_GROUP " +
                "SET PRODUCT_STATUS = :productStatus " +
                "WHERE ID = :productGroupId";

        List<Map<String, Object>> batchValues = productGroupIds.stream()
                .map(aLong -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("productStatus", productStatus.name())
                            .addValue("productGroupId", aLong);
                    return params.getValues();
                })
                .toList();
        namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }




    public void batchUpdateStyleCodes(List<ProductStyleCodeDto> productStyleCodes){
        String sql = "UPDATE PRODUCT_GROUP " +
                "SET STYLE_CODE = :styleCode " +
                "WHERE ID = :productGroupId";

        List<Map<String, Object>> batchValues = productStyleCodes.stream()
                .map(styleCode -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("styleCode", styleCode.styleCode())
                            .addValue("productGroupId", styleCode.productGroupId());
                    return params.getValues();
                })
                .toList();
        namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));

    }


}
