package com.ryuqq.setof.storage.db.core.product.option.detail;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class OptionDetailJdbcRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public OptionDetailJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void softDeleteAll(List<Long> optionDetailIds) {
        String sql = "UPDATE OPTION_DETAIL " +
                "SET DELETE_YN = :deleteYn " +
                "WHERE ID = :optionDetailId ";

        List<Map<String, Object>> batchValues = optionDetailIds.stream()
                .map(Long -> {
                    MapSqlParameterSource params = new MapSqlParameterSource()
                            .addValue("optionDetailId", Long)
                            .addValue("deleteYn", true);
                    return params.getValues();
                })
                .toList();

        namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[0]));
    }



}
