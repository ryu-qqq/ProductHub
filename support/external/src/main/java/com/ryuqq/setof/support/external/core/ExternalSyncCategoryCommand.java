package com.ryuqq.setof.support.external.core;

public record ExternalSyncCategoryCommand(
        String externalCategoryId,
        String externalExtraCategoryId,
        String description
) {
}
