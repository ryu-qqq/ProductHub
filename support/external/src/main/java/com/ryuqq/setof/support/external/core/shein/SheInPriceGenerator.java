package com.ryuqq.setof.support.external.core.shein;


import com.ryuqq.setof.support.utils.Money;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 외부 몰 (SheIn) 가격 생성기
 *
 * 이 클래스는 세토프 판매가와 환율을 기반으로 외부 몰에 맞는 최종 판매 가격을 계산합니다.
 * 계산 로직은 특정 비즈니스 규칙을 준수하며,
 * 아래 주요 단계를 포함합니다:
 *
 * 1. 기본 가격 계산: 판매가와 환율을 기반으로 기본 가격 산출.
 * 2. 최종 가격 반환: 모든 계산이 끝난 후 배송비(39달러)를 포함한 최종 가격 결정.
 */

@Component
public class SheInPriceGenerator {
    /**
     * 최종 가격을 계산합니다.
     *
     * @param currentPrice 세토프 판매가 (현재 가격 및 정가 포함).
     * @param exchangeRate 환율 (엔/원).
     * @return 최종 외부 몰 가격 (배송비 포함).
     */
    public BigDecimal calculateFinalPrice(Money currentPrice, BigDecimal exchangeRate) {
        return calculateBasePrice(currentPrice, exchangeRate);

    }

    /**
     * 기본 가격 계산 로직.
     *
     * @param currentPrice 현재 판매가.
     * @param exchangeRate 정규화된 환율.
     * @return 기본 가격
     */
    private BigDecimal calculateBasePrice(Money currentPrice, BigDecimal exchangeRate) {
        return currentPrice
                .times(1.1) // 가격에 10% 마진 추가 (세토프판매가 * 1.1)
                .divide(Money.wons(exchangeRate), 10, RoundingMode.CEILING) // 환율 적용
                .divide(BigDecimal.TEN, 0, RoundingMode.UP) // 십의 자리에서 올림
                .multiply(BigDecimal.TEN) // 천 단위 절삭 후 복원
                .add(BigDecimal.valueOf(39)); // 배송비 39달러 추가
    }

}