package com.ryuqq.setof.domain.core.product;

public interface UpdateChecker<T, U>  {

    /**
     * 업데이트 여부를 판단
     *
     * @param existing 기존 도메인 데이터
     * @param updated  업데이트 요청 데이터
     * @return UpdateDecision (업데이트 여부 및 변경 필드 정보)
     */

    UpdateDecision checkUpdates(T existing, U updated);

}
