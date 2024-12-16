package com.ryuqq.setof.support.external.core.shein;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Info<T> {

    private final T info;

    @JsonCreator
    public Info(@JsonProperty("info") T info) {
        this.info = info;
    }

    public T getInfo() {
        return info;
    }
}
