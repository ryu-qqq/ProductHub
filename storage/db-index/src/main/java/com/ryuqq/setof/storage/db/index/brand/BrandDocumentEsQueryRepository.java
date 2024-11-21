package com.ryuqq.setof.storage.db.index.brand;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQueryField;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.ryuqq.setof.storage.db.index.ElasticsearchQueryFactory;
import com.ryuqq.setof.storage.db.index.ElasticsearchQueryTemplate;
import com.ryuqq.setof.storage.db.index.brand.dto.BrandIndexFilterDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BrandDocumentEsQueryRepository {

    private final ElasticsearchQueryTemplate elasticsearchQueryTemplate;
    private static final String INDEX_NAME = "brand-document";

    public BrandDocumentEsQueryRepository(ElasticsearchQueryTemplate elasticsearchQueryTemplate) {
        this.elasticsearchQueryTemplate = elasticsearchQueryTemplate;
    }

    public List<BrandDocument> fetchBrands(BrandIndexFilterDto brandFilter) {
        List<Query> mustQueries = new ArrayList<>();

        if (brandFilter.brandIds() != null && !brandFilter.brandIds().isEmpty()) {
            Query termsQuery = ElasticsearchQueryFactory.createTermsQuery("brandId",
                    brandFilter.brandIds().stream().map(String::valueOf).toList());
            mustQueries.add(termsQuery);
        }

        if (brandFilter.brandName() != null) {
            Query matchQuery = ElasticsearchQueryFactory.createMatchQuery(
                    List.of("brandName", "brandNameKr"), brandFilter.brandName()
            );
            mustQueries.add(matchQuery);
        }

//            if (brandFilter.cursorId() != null) {
//                Query rangeQuery = RangeQuery.of(r -> r.number(n ->n
//                                .field("_id")
//                                .gt(Double.valueOf(brandFilter.cursorId()))
//                                )
//
//
//                )._toQuery();
//
//                boolQuery.must(rangeQuery);
//            }

        BoolQuery boolQuery = ElasticsearchQueryFactory.createBoolQuery(mustQueries);

        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index("brand-document")
                .query(q -> q.bool(boolQuery))
                .size(brandFilter.pageSize())
                .sort(srt -> srt.field(f -> f.field("_id").order(SortOrder.Asc)))
        );

        SearchResponse<BrandDocument> searchResponse = elasticsearchQueryTemplate.executeSearch(
                this.getClass().getName(),
                searchRequest,
                BrandDocument.class
        );

        return searchResponse.hits().hits().stream()
                .map(Hit::source)
                .toList();
    }

    public long fetchBrandCount(BrandIndexFilterDto brandFilter) {
        List<Query> mustQueries = new ArrayList<>();

        if (brandFilter.brandIds() != null && !brandFilter.brandIds().isEmpty()) {
            Query termsQuery = ElasticsearchQueryFactory.createTermsQuery("brandId",
                    brandFilter.brandIds().stream().map(String::valueOf).toList());
            mustQueries.add(termsQuery);
        }

        if (brandFilter.brandName() != null) {
            Query matchQuery = ElasticsearchQueryFactory.createMatchQuery(
                    List.of("brandName", "brandNameKr"), brandFilter.brandName()
            );
            mustQueries.add(matchQuery);
        }

        BoolQuery boolQuery = ElasticsearchQueryFactory.createBoolQuery(mustQueries);

        co.elastic.clients.elasticsearch.core.CountRequest countRequest = co.elastic.clients.elasticsearch.core.CountRequest.of(c -> c
                .index(INDEX_NAME)
                .query(q -> q.bool(boolQuery))
        );

        return elasticsearchQueryTemplate.executeCount(this.getClass().getName(), countRequest);
    }

    public List<BrandDocument> fetchBrands(List<String> brandNames) {
        List<FieldValue> fieldValues = brandNames.stream()
                .map(FieldValue::of)
                .collect(Collectors.toList());

        BoolQuery.Builder boolQuery = new BoolQuery.Builder()
                .must(q -> q.terms(
                        t -> t.field("brandName")
                                .terms(TermsQueryField.of(f -> f.value(fieldValues)))
                ));

        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index(INDEX_NAME)
                .query(q -> q.bool(boolQuery.build()))
                .size(brandNames.size())
        );

        SearchResponse<BrandDocument> searchResponse = elasticsearchQueryTemplate.executeSearch(
                this.getClass().getName(),
                searchRequest,
                BrandDocument.class
        );

        return searchResponse.hits().hits().stream()
                .map(Hit::source)
                .toList();
    }

}
