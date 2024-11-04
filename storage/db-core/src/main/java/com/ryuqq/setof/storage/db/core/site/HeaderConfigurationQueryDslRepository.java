package com.ryuqq.setof.storage.db.core.site;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.storage.db.core.site.dto.HeaderConfigurationDto;
import com.ryuqq.setof.storage.db.core.site.dto.QHeaderConfigurationDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ryuqq.setof.storage.db.core.site.QHeaderConfigurationEntity.headerConfigurationEntity;

@Repository
public class HeaderConfigurationQueryDslRepository {

    private final JPAQueryFactory queryFactory;


    public HeaderConfigurationQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }


    public List<HeaderConfigurationDto> fetchConfigurationsByName(String configurationName) {
        return queryFactory.select(
                        new QHeaderConfigurationDto(
                                headerConfigurationEntity.configurationName,
                                headerConfigurationEntity.headerName,
                                headerConfigurationEntity.headerValue,
                                headerConfigurationEntity.isActive,
                                headerConfigurationEntity.description
                        ))
                .from(headerConfigurationEntity)
                .where(
                        headerConfigurationEntity.configurationName.eq(configurationName),
                        headerConfigurationEntity.isActive.isTrue()
                )
                .fetch();
    }

}
