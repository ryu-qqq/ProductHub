package com.ryuqq.setof.support.external.core.buyma;


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
public class BuyMaPriceGenerator {
    /**
     * 최종 가격을 계산합니다.
     *
     * @param currentPrice 세토프 판매가 (현재 가격 및 정가 포함).
     * @param exchangeRate 환율 (엔/원).
     * @return 최종 외부 몰 가격 (배송비 포함).
     */
    public BigDecimal calculateFinalPrice(Money currentPrice, BigDecimal exchangeRate) {

        // 환율 계산: 100엔 단위로 나눠 환율을 정규화 (예: 907 -> 9.07).
        BigDecimal normalizedExchangeRate = exchangeRate.divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);

        // 기본 가격 계산: 판매가와 환율을 기반으로 기본 가격 산출.
        BigDecimal basePrice = calculateBasePrice(currentPrice, normalizedExchangeRate);

        // 기본 가격 + 배송비(900엔)를 포함하여 기준 초과 여부 판단.
        BigDecimal baseWithShipping = basePrice.add(BigDecimal.valueOf(900));

        // 최종 가격 결정:
        // - 기준 초과(16666엔) 시, 추가 마진 적용한 가격 계산.
        // - 기준 이하일 경우, 기본 가격 유지.
        BigDecimal finalPrice = baseWithShipping.compareTo(BigDecimal.valueOf(16666)) > 0
                ? calculateAdjustedPrice(currentPrice, normalizedExchangeRate)
                : basePrice;

        // 최종 가격 + 배송비(항상 900엔 추가)
        return ensureHundredsPlaceIs900(finalPrice);
    }

    /**
     * 기본 가격 계산 로직.
     *
     * @param currentPrice 현재 판매가.
     * @param exchangeRate 정규화된 환율.
     * @return 기본 가격 (천 단위 반올림 적용).
     */
    private BigDecimal calculateBasePrice(Money currentPrice, BigDecimal exchangeRate) {
        return currentPrice
                .times(1.1) // 가격에 10% 마진 추가
                .divide(Money.wons(exchangeRate), 0, RoundingMode.CEILING) // 환율 적용
                .divide(BigDecimal.valueOf(1000), RoundingMode.CEILING) // 천 단위 절삭
                .multiply(BigDecimal.valueOf(1000)); // 천 단위 반올림
    }

    /**
     * 조정된 가격 계산 로직.
     * - 기준 초과(16666엔) 시, 추가 마진 적용.
     *
     * @param currentPrice 현재 판매가.
     * @param exchangeRate 정규화된 환율.
     * @return 조정된 가격 (천 단위 반올림 적용).
     */
    private BigDecimal calculateAdjustedPrice(Money currentPrice, BigDecimal exchangeRate) {
        return currentPrice
                .times(1.1) // 가격에 10% 마진 추가
                .divide(Money.wons(exchangeRate), 0, RoundingMode.CEILING) // 환율 적용
                .multiply(BigDecimal.valueOf(1.1)) // 10% 추가 마진
                .multiply(BigDecimal.valueOf(1.1)) // 추가 마진 적용
                .divide(BigDecimal.valueOf(1000), RoundingMode.CEILING) // 천 단위 절삭
                .multiply(BigDecimal.valueOf(1000)); // 천 단위 반올림
    }

    /**
     * 최종 가격의 100자리 부분을 항상 900으로 보정합니다.
     *
     * @param value 조정 전 최종 가격.
     * @return 100자리 부분이 900으로 설정된 최종 가격.
     */
    private BigDecimal ensureHundredsPlaceIs900(BigDecimal value) {
        return value.divide(BigDecimal.valueOf(1000), 0, RoundingMode.DOWN) // 천 단위로 나눠 버림.
                .multiply(BigDecimal.valueOf(1000)) // 천 단위로 반올림.
                .add(BigDecimal.valueOf(900)); // 항상 900엔 추가.
    }

}