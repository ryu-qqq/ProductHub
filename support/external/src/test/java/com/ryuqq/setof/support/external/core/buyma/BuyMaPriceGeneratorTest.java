package com.ryuqq.setof.support.external.core.buyma;


import com.ryuqq.setof.support.utils.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BuyMaPriceGeneratorTest {

    private final BuyMaPriceGenerator buyMaPriceGenerator = new BuyMaPriceGenerator();

    @Test
    void test_calculateFinalPrice_belowThreshold() {
        Money currentPrice = Money.wons(BigDecimal.valueOf(150000));
        BigDecimal exchangeRate = BigDecimal.valueOf(900);

        // When
        BigDecimal finalPrice = buyMaPriceGenerator.calculateFinalPrice(currentPrice, exchangeRate);

        // Then
        BigDecimal expected = new BigDecimal("22900"); // 예상 결과: (100000 * 1.1 / 900) + 900
        assertEquals(expected, finalPrice, "Threshold 이하의 최종 가격이 올바르지 않습니다.");
    }

    @Test
    void test_calculateFinalPrice_aboveThreshold() {
        // Given
        Money currentPrice = Money.wons(BigDecimal.valueOf(250000));
        BigDecimal exchangeRate = BigDecimal.valueOf(900);

        // When
        BigDecimal finalPrice = buyMaPriceGenerator.calculateFinalPrice(currentPrice, exchangeRate);

        // Then
        BigDecimal expected = new BigDecimal("36900"); // 예상 결과: 조정된 가격 + 900
        assertEquals(expected, finalPrice, "Threshold 초과의 최종 가격이 올바르지 않습니다.");
    }

    @Test
    void test_calculateFinalPrice_exactThreshold() {
        // Given
        Money currentPrice = Money.wons(BigDecimal.valueOf(150000));
        BigDecimal exchangeRate = BigDecimal.valueOf(900);

        // When
        BigDecimal finalPrice = buyMaPriceGenerator.calculateFinalPrice(currentPrice, exchangeRate);

        // Then
        BigDecimal expected = new BigDecimal("22900"); // 예상 결과: 기준값 (16666엔)
        assertEquals(expected, finalPrice, "Threshold와 동일한 경우 최종 가격이 올바르지 않습니다.");
    }

    @Test
    void test_calculateFinalPrice_hundredsPlaceIs900() {
        // Given
        Money currentPrice = Money.wons(BigDecimal.valueOf(15000));
        BigDecimal exchangeRate = BigDecimal.valueOf(900);

        // When
        BigDecimal finalPrice = buyMaPriceGenerator.calculateFinalPrice(currentPrice, exchangeRate);

        // Then
        // 100자리 부분이 900인지 확인
        assertEquals(finalPrice.remainder(BigDecimal.valueOf(1000)), BigDecimal.valueOf(900), "최종 가격의 100자리가 900으로 보정되지 않았습니다.");
    }

    @Test
    void test_calculateFinalPrice_withHighExchangeRate() {
        // Given
        Money currentPrice = Money.wons(BigDecimal.valueOf(550000));
        BigDecimal exchangeRate = BigDecimal.valueOf(1500);

        // When
        BigDecimal finalPrice = buyMaPriceGenerator.calculateFinalPrice(currentPrice, exchangeRate);

        // Then
        BigDecimal expected = new BigDecimal("48900"); // 예상 결과: 조정된 가격 + 900
        assertEquals(expected, finalPrice, "높은 환율에서 최종 가격이 올바르지 않습니다.");
    }

    @Test
    void test_calculateFinalPrice_withLowExchangeRate() {
        // Given
        Money currentPrice = Money.wons(BigDecimal.valueOf(550000));
        BigDecimal exchangeRate = BigDecimal.valueOf(500);

        // When
        BigDecimal finalPrice = buyMaPriceGenerator.calculateFinalPrice(currentPrice, exchangeRate);

        // Then
        BigDecimal expected = new BigDecimal("146900"); // 예상 결과: 조정된 가격 + 900
        assertEquals(expected, finalPrice, "낮은 환율에서 최종 가격이 올바르지 않습니다.");
    }
}
