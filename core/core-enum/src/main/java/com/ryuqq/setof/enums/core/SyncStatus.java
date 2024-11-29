package com.ryuqq.setof.enums.core;

public enum SyncStatus {

    WAITING,
    PROCESSING,
    REVIEW,
    UPLOADING,
    APPROVED,
    SUCCESS,
    FAILED,
    ;

    public boolean isApproved(){
        return this.equals(APPROVED);
    }


}
