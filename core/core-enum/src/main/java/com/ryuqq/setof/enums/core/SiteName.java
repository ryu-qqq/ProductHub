package com.ryuqq.setof.enums.core;

import java.util.Arrays;
import java.util.List;

public enum SiteName {
    MUSTIT,
    KASINA,
    BUYMA,
    SHEIN,
    SELLIC,
    OCO,
    NAVER

    ;


    public static SiteName of(String siteName) {
        return Arrays.stream(SiteName.values())
                .filter(name -> name.name().equalsIgnoreCase(siteName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown site name: " + siteName));
    }

    public static List<SiteName> getExternalSyncSite(){
        return Arrays.asList(BUYMA, OCO, SHEIN);
    }

}
