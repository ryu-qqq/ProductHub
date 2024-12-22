package com.ryuqq.setof.storage.db.core.site.external;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ExternalCategoryOptionJdbcRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ExternalCategoryOptionJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int[] batchInsertExternalCategoryOptions(List<ExternalCategoryOptionEntity> externalCategoryOptionEntities) {
        String sql = "INSERT INTO EXTERNAL_CATEGORY_OPTION " +
                "(SITE_ID, EXTERNAL_CATEGORY_ID, OPTION_GROUP_ID, OPTION_DETAIL_ID, OPTION_VALUE) " +
                "VALUES (:siteId, :externalCategoryId, :optionGroupId, :optionDetailId, :optionValue)";

        List<Map<String, Object>> batchValues = externalCategoryOptionEntities.stream()
                .map(option -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("siteId", option.getSiteId())
                            .addValue("externalCategoryId", option.getExternalCategoryId())
                            .addValue("optionGroupId", option.getOptionGroupId())
                            .addValue("optionDetailId", option.getOptionId())
                            .addValue("optionValue", option.getOptionValue());
                    return params.getValues();
                })
                .toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }


}
