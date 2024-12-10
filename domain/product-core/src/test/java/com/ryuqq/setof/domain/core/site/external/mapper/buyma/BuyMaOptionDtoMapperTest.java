package com.ryuqq.setof.domain.core.site.external.mapper.buyma;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class BuyMaOptionDtoMapperTest {


    @Test
    void numeric_test(){
        Double v = extractNumericPart("22.5cm (US5)");
        System.out.println("v = " + v);
    }

    private Double extractNumericPart(String value) {
        try {
            // 정규식을 사용하여 숫자와 단위를 추출
            Pattern pattern = Pattern.compile("([0-9.]+)(cm|mm)");
            Matcher matcher = pattern.matcher(value);

            if (matcher.find()) {
                String numeric = matcher.group(1); // 숫자 부분 추출
                String unit = matcher.group(2); // 단위 추출

                // 숫자 변환 및 범위 확인
                double size = Double.parseDouble(numeric);
                if (unit.equals("cm") && size >= 20 && size <= 40) { // cm 범위
                    return size;
                } else if (unit.equals("mm") && size >= 200 && size <= 400) { // mm 범위
                    return size;
                }
            }
            return null; // 유효하지 않은 경우 null 반환
        } catch (NumberFormatException e) {
            return null;
        }
    }

}