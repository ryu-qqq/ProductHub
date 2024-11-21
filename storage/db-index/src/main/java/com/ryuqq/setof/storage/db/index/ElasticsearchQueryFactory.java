package com.ryuqq.setof.storage.db.index;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQueryField;

import java.util.List;

public class ElasticsearchQueryFactory {

    /**
     * BoolQuery 생성 메서드
     *
     * @param mustQueries must 조건으로 추가할 쿼리 리스트
     * @return BoolQuery
     */
    public static BoolQuery createBoolQuery(List<Query> mustQueries) {
        BoolQuery.Builder boolQueryBuilder = new BoolQuery.Builder();
        mustQueries.forEach(boolQueryBuilder::must);
        return boolQueryBuilder.build();
    }

    /**
     * MatchQuery 생성 메서드
     *
     * @param fields 필드 이름
     * @param value 매칭할 값
     * @return MatchQuery를 포함한 Query
     */
    public static Query createMatchQuery(List<String> fields, String value) {
        BoolQuery.Builder boolQueryBuilder = new BoolQuery.Builder();

        fields.forEach(field -> {
            Query matchQuery = MatchQuery.of(m -> m
                    .field(field)
                    .query(value)
            )._toQuery();
            boolQueryBuilder.should(matchQuery);
        });

        return Query.of(q -> q.bool(boolQueryBuilder.build()));
    }


    /**
     * TermsQuery 생성 메서드
     *
     * @param field 필드 이름
     * @param values 매칭할 값 리스트
     * @return TermsQuery를 포함한 Query
     */
    public static Query createTermsQuery(String field, List<String> values) {
        List<FieldValue> fieldValues = values.stream()
                .map(FieldValue::of)
                .toList();

        return Query.of(q -> q.terms(t -> t
                .field(field)
                .terms(TermsQueryField.of(tf -> tf.value(fieldValues)))
        ));
    }

}
