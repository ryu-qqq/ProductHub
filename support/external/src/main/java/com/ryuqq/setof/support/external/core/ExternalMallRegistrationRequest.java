package com.ryuqq.setof.support.external.core;

public record ExternalMallRegistrationRequest(
        long productGroupId,
        long siteId,
        ExternalMallProductPayload externalMallProductPayload
) {}
