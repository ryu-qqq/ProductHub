package com.ryuqq.setof.enums.core;

import java.util.Arrays;

public enum SiteName {
    MUSTIT,
    KASINA,
    BUYMA,
    SHEIN,
    NAVER

    ;


    public static SiteName of(String siteName) {
        return Arrays.stream(SiteName.values())
                .filter(name -> name.name().equalsIgnoreCase(siteName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown site name: " + siteName));
    }

}
