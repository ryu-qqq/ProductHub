package com.ryuqq.setof.api.core.controller.v1.category.request;

public record CategoryRelationGetRequestDto(
        long categoryId,
        boolean isParentRelation
) {
}
