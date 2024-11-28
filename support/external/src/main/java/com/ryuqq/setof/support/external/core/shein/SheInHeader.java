package com.ryuqq.setof.support.external.core.shein;

public record SheInHeader(
        String openKeyId,
        String timestamp,
        String signature
) {
}
