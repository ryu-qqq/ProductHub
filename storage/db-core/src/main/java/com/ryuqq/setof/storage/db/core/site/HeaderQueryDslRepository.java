package com.ryuqq.setof.storage.db.core.site;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.storage.db.core.site.dto.HeaderDto;
import com.ryuqq.setof.storage.db.core.site.dto.QHeaderDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ryuqq.setof.storage.db.core.site.QHeaderEntity.headerEntity;

@Repository
public class HeaderQueryDslRepository {

    private final JPAQueryFactory queryFactory;


    public HeaderQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }


    public List<HeaderDto> fetchMultipleRandomHeaders(int limit) {
        return queryFactory.select(
                        new QHeaderDto(
                                headerEntity.headerName,
                                headerEntity.headerValue,
                                headerEntity.weight,
                                headerEntity.isRequired,
                                headerEntity.priority,
                                headerEntity.notes
                        ))
                .from(headerEntity)
                .orderBy(com.querydsl.core.types.dsl.Expressions.numberTemplate(Double.class, "function('RAND')").asc())
                .limit(limit)
                .fetch();
    }

}
