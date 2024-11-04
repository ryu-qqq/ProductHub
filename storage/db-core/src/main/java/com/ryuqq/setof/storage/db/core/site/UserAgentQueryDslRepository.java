package com.ryuqq.setof.storage.db.core.site;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.storage.db.core.site.dto.QUserAgentDto;
import com.ryuqq.setof.storage.db.core.site.dto.UserAgentDto;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.ryuqq.setof.storage.db.core.site.QUserAgentsEntity.userAgentsEntity;

@Repository
public class UserAgentQueryDslRepository  {

    private final JPAQueryFactory queryFactory;

    public UserAgentQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }


    public Optional<UserAgentDto> fetchRandomUserAgent() {
        return Optional.ofNullable(
                queryFactory.select(
                            new QUserAgentDto(
                                    userAgentsEntity.userAgent,
                                    userAgentsEntity.weight,
                                    userAgentsEntity.deviceType,
                                    userAgentsEntity.os,
                                    userAgentsEntity.browserVersion
                            )
                        )
                        .from(userAgentsEntity)
                        .orderBy(com.querydsl.core.types.dsl.Expressions.numberTemplate(Double.class, "function('RAND')").asc())
                        .fetchFirst()
        );
    }


}
