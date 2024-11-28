package com.ryuqq.setof.storage.db.core.category;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MappingCategoryJdbcRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public MappingCategoryJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int[] batchInsertMappingCategories(List<MappingCategoryEntity> mappingCategoryEntities) {

        String sql = "INSERT MAPPING_CATEGORY(SITE_ID, SITE_CATEGORY_ID, STIE_CATEGORY_EXTRA_ID, INTERNAL_CATEGORY_ID, DESCRIPTOIN )" +
                "VALUES(:siteId, :siteCategoryId, :siteCategoryExtraId, :internalCategoryId, :description)";

        List<Map<String, Object>> batchValues = mappingCategoryEntities.stream()
                .map(category -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("siteId", category.getSiteId())
                            .addValue("siteCategoryId", category.getSiteCategoryId())
                            .addValue("siteCategoryExtraId", category.getSiteCategoryExtraId())
                            .addValue("internalCategoryId", category.getInternalCategoryId())
                            .addValue("description", category.getDescription());
                    return params.getValues();
        }).toList();

        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }


}
