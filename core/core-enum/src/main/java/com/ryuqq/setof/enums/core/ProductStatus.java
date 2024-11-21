package com.ryuqq.setof.enums.core;

public enum ProductStatus {

    WAITING,
    PROCESSING,
    APPROVED,
    FAILED,
    UPDATE_WAITING,
    UPDATE_PROCESSING,
    UPDATE_FAILED
    ;

    public boolean isApproved(){
        return this.equals(APPROVED);
    }

    public boolean isFailed(){
        return this.equals(FAILED);
    }



}
