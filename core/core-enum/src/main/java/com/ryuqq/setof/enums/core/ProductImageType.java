package com.ryuqq.setof.enums.core;

public enum ProductImageType {
    MAIN,
    DETAIL;


    public boolean isMain(){
        return this == MAIN;
    }

    public boolean isDetail(){
        return this == DETAIL;
    }

}
