package com.ryuqq.setof.storage.db.core.category;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.Union;
import com.ryuqq.setof.enums.core.CategoryType;
import com.ryuqq.setof.enums.core.TargetGroup;
import com.ryuqq.setof.storage.db.core.category.dto.CategoryDto;
import com.ryuqq.setof.storage.db.core.category.dto.CategoryStorageFilterDto;
import com.ryuqq.setof.storage.db.core.category.dto.QCategoryDto;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ryuqq.setof.storage.db.core.category.QCategoryEntity.categoryEntity;


@Repository
public class CategoryQueryDslRepository implements CategoryQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;


    public CategoryQueryDslRepository(JPAQueryFactory queryFactory, EntityManager em) {
        this.queryFactory = queryFactory;
        this.em = em;
    }

    @Override
    public boolean fetchCategoryExists(long categoryId) {
        Long categoryOpt = queryFactory
                .select(categoryEntity.id)
                .from(categoryEntity)
                .where(categoryIdEq(categoryId))
                .fetchFirst();

        return categoryOpt != null;
    }

    @Override
    public List<CategoryDto> fetchCategories(CategoryStorageFilterDto categoryFilter) {
        return queryFactory
                .select(new QCategoryDto(
                        categoryEntity.id,
                        categoryEntity.categoryName,
                        categoryEntity.depth,
                        categoryEntity.parentCategoryId,
                        categoryEntity.displayYn,
                        categoryEntity.targetGroup,
                        categoryEntity.categoryType,
                        categoryEntity.path
                ))
                .from(categoryEntity)
                .orderBy(categoryEntity.id.desc())
                .limit(categoryFilter.pageSize())
                .where(
                        categoryIdIn(categoryFilter.categoryIds()),
                        targetGroupEp(categoryFilter.targetGroup()),
                        categoryTypeEp(categoryFilter.categoryType()),
                        isCategoryIdLt(categoryFilter.cursorId())
                )
                .fetch();

    }

    @Override
    public long fetchCategoryCount(CategoryStorageFilterDto categoryFilter) {
        Long count =  queryFactory.select(
                        categoryEntity.count()
                )
                .from(categoryEntity)
                .where(
                        categoryIdIn(categoryFilter.categoryIds()),
                        targetGroupEp(categoryFilter.targetGroup()),
                        categoryTypeEp(categoryFilter.categoryType())
                ).fetchOne();

        return count !=null ? count : 0;
    }

    @Override
    public List<CategoryDto> fetchChildCategories(long categoryId) {
        return fetchRecursiveCategories(categoryId, true);
    }

    @Override
    public List<CategoryDto> fetchParentCategories(long categoryId) {
        return fetchRecursiveCategories(categoryId, false);
    }

    private List<CategoryDto> fetchRecursiveCategories(long categoryId, boolean isChild) {
        JPASQLQuery<CategoryEntity> q = new JPASQLQuery<>(em, SQLTemplates.DEFAULT);
        QCategoryEntity c = new QCategoryEntity("c");
        QCategoryEntity sub = new QCategoryEntity("sub");
        EntityPathBase<QCategoryEntity> rec = new EntityPathBase<>(QCategoryEntity.class, "sub");

        JPQLQuery<CategoryEntity> query1 = JPAExpressions.select(Projections.fields(CategoryEntity.class, c.id, c.categoryName, c.depth, c.parentCategoryId, c.displayYn, c.targetGroup, c.categoryType,  c.path))
                .from(c)
                .where(c.id.eq(categoryId));

        JPQLQuery<CategoryEntity> query2 = isChild
                ? JPAExpressions.select(Projections.fields(CategoryEntity.class, c.id, c.categoryName, c.depth, c.parentCategoryId, c.displayYn, c.targetGroup, c.categoryType,  c.path))
                .from(rec)
                .innerJoin(c).on(sub.id.eq(c.parentCategoryId))
                : JPAExpressions.select(Projections.fields(CategoryEntity.class, c.id, c.categoryName, c.depth, c.parentCategoryId, c.displayYn, c.targetGroup, c.categoryType,  c.path))
                .from(rec)
                .innerJoin(c).on(c.id.eq(sub.parentCategoryId));

        Union<CategoryEntity> union = SQLExpressions.unionAll(query1, query2);

        return q.withRecursive(rec, c.id, c.categoryName, c.depth, c.parentCategoryId, c.displayYn, c.targetGroup, c.categoryType, c.path).as(union)
                .select(
                        new QCategoryDto(
                                sub.id,
                                sub.categoryName,
                                sub.depth,
                                sub.parentCategoryId,
                                sub.displayYn,
                                sub.targetGroup,
                                sub.categoryType,
                                sub.path
                        ))
                .from(rec)
                .orderBy(isChild ? sub.depth.asc() : sub.depth.desc())
                .fetch();

    }

    private BooleanExpression categoryIdEq(long categoryId){
        return categoryEntity.id.eq(categoryId);
    }

    private BooleanExpression categoryIdIn(List<Long> categoryIds){
        return (categoryIds != null && !categoryIds.isEmpty()) ? categoryEntity.id.in(categoryIds) : null;
    }

    private BooleanExpression targetGroupEp(TargetGroup targetGroup){
        if(targetGroup != null) return categoryEntity.targetGroup.eq(targetGroup);
        return null;
    }

    private BooleanExpression categoryTypeEp(CategoryType categoryType){
        if(categoryType != null) return categoryEntity.categoryType.eq(categoryType);
        return null;
    }

    private BooleanExpression isCategoryIdLt(Long categoryId){
        if(categoryId !=null) return categoryEntity.id.lt(categoryId);
        else return null;
    }

}
