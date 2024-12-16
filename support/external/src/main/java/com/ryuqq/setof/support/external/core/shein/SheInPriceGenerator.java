package com.ryuqq.setof.support.external.core.shein;


import com.ryuqq.setof.support.utils.Money;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 외부 몰 (BuyMa) 가격 생성기
 *
 * 이 클래스는 세토프 판매가와 환율을 기반으로 외부 몰에 맞는 최종 판매 가격을 계산합니다.
 * 계산 로직은 특정 비즈니스 규칙을 준수하며,
 * 아래 주요 단계를 포함합니다:
 *
 * 1. 기본 가격 계산: 판매가와 환율을 기반으로 기본 가격 산출.
 * 2. 가격 초과 여부 판단: 배송비 포함 기본 가격이 특정 기준(16666엔)을 초과하는지 확인.
 * 3. 조정된 가격 계산: 초과한 경우 추가 마진 적용.
 * 4. 최종 가격 반환: 모든 계산이 끝난 후 배송비(900엔)를 포함한 최종 가격 결정.
 */

@Component
public class SheInPriceGenerator {
    /**
     * 최종 가격을 계산합니다.
     *
     * @param currentPrice 세토프 판매가 (현재 가격 및 정가 포함).
     * @param exchangeRate 환율 (달러/원).
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
     * @return 기본 가격 (천 단위 반올림 적용).
     */
    private BigDecimal calculateBasePrice(Money currentPrice, BigDecimal exchangeRate) {

        BigDecimal basePrice = currentPrice
                .times(1.1)
                .divide(Money.wons(exchangeRate), 0, RoundingMode.CEILING); // 환율 계산 후 올림

        return basePrice
                .add(BigDecimal.valueOf(9))
                .add(BigDecimal.valueOf(30));
    }


}