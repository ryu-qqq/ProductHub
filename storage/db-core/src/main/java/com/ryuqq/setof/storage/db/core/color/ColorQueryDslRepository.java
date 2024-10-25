package com.ryuqq.setof.storage.db.core.color;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryuqq.setof.domain.core.color.Color;
import com.ryuqq.setof.domain.core.color.ColorFilter;
import com.ryuqq.setof.domain.core.color.ColorQueryRepository;
import com.ryuqq.setof.storage.db.core.color.dao.ColorDao;
import com.ryuqq.setof.storage.db.core.color.dao.QColorDao;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ryuqq.setof.storage.db.core.color.QColorEntity.colorEntity;

@Repository
public class ColorQueryDslRepository implements ColorQueryRepository {

    private final JPAQueryFactory queryFactory;


    public ColorQueryDslRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public boolean fetchColorExists(long colorId) {
        Long categoryOpt = queryFactory
                .select(colorEntity.id)
                .from(colorEntity)
                .where(colorIdEq(colorId))
                .fetchFirst();

        return categoryOpt != null;
    }

    @Override
    public List<Color> fetchColors(ColorFilter colorFilter) {
        List<ColorDao> categories = queryFactory
                .select(new QColorDao(
                        colorEntity.id,
                        colorEntity.colorName
                ))
                .from(colorEntity)
                .orderBy(colorEntity.id.desc())
                .limit(colorFilter.pageSize())
                .where(
                        colorIdIn(colorFilter.colorIds()),
                        isColorIdLt(colorFilter.cursorId())
                )
                .fetch();

        return toDomain(categories);
    }


    @Override
    public long fetchColorCount(ColorFilter colorFilter) {
        Long count =  queryFactory.select(
                        colorEntity.count()
                )
                .from(colorEntity)
                .where(
                        colorIdIn(colorFilter.colorIds())
                ).fetchOne();

        return count !=null ? count : 0;
    }


    private List<Color> toDomain(List<ColorDao> colors){
        return colors.stream().map(ColorDao::toColor).toList();
    }

    private BooleanExpression colorIdEq(long colorId) {
        return colorEntity.id.eq(colorId);
    }

    private BooleanExpression colorIdIn(List<Long> colorIds){
        return (colorIds != null && !colorIds.isEmpty()) ? colorEntity.id.in(colorIds) : null;
    }

    private BooleanExpression isColorIdLt(Long colorId){
        if(colorId !=null) return colorEntity.id.lt(colorId);
        else return null;
    }

}
