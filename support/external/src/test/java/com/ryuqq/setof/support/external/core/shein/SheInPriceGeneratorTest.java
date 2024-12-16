package com.ryuqq.setof.support.external.core.shein;

import com.ryuqq.setof.support.utils.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


import static org.junit.jupiter.api.Assertions.assertEquals;

class SheInPriceGeneratorTest {

    private final SheInPriceGenerator SheInPriceGenerator = new SheInPriceGenerator();

    @Test
    void calculateBasePriceTest() {

        Money currentPrice = Money.wons(41870); // 32,450원
        BigDecimal exchangeRate = BigDecimal.valueOf(1436.40); // 환율 1432.5

        BigDecimal expectedBasePrice = SheInPriceGenerator.calculateFinalPrice(currentPrice, exchangeRate);

        BigDecimal actualBasePrice = BigDecimal.valueOf(72);

        // Then
        assertEquals(expectedBasePrice, actualBasePrice, "The calculated base price should match the expected value.");
    }

}