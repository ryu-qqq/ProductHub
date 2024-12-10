package com.ryuqq.setof.support.external.core.oco;


import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.support.external.core.ExternalMallPriceContext;
import com.ryuqq.setof.support.utils.Money;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 외부 몰 (Oco) 가격 생성기
 *
 * 이 클래스는 세토프 판매가와 외부 몰에 맞는 최종 판매 가격을 계산합니다.
 * 계산 로직은 특정 비즈니스 규칙을 준수하며,
 * 아래 주요 단계를 포함합니다:
 *
 * 1. 기본 가격 계산: 판매가와 수수료 1.25 배를 곱한 가격
 */

@Component
public class OcoPriceGenerator {
    /**
     * 최종 가격을 계산합니다.
     *
     * @param regularPrice 세토프 참고 판매가
     * @param currentPrice 세토프 현재 판매가
     * @return 최종 외부 몰 가격.
     */
    public ExternalMallPriceContext calculateFinalPrice(Money regularPrice, Money currentPrice) {
        int newCurrentPrice = currentPrice.times(1.25).getAmount().intValue();
        int newRegularPrice = Math.max(regularPrice.getAmount().intValueExact(), newCurrentPrice);
        return new ExternalMallPriceContext(BigDecimal.valueOf(newRegularPrice), BigDecimal.valueOf(newCurrentPrice), Origin.KR);
    }

}