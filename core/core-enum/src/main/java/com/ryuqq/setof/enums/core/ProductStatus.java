package com.ryuqq.setof.enums.core;

public enum ProductStatus {

    WAITING,
    APPROVED,
    FAILED,
    UPDATE_WAITING,
    UPDATE_FAILED
    ;

    public boolean isApproved(){
        return this.equals(APPROVED);
    }

    public boolean isFailed(){
        return this.equals(FAILED);
    }



}
