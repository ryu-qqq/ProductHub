package com.ryuqq.setof.domain.core.category;

import java.util.List;

public record CategoryRelation(
        long categoryId,
        List<Category> categories,
        boolean parentRelation
) {
}
