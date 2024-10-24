package com.ryuqq.setof.storage.db.core;

import jakarta.persistence.AttributeConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BooleanToIntegerConverterTest {

    private AttributeConverter<Boolean, Integer> converter;

    @BeforeEach
    public void setUp() {
        converter = new BooleanToIntegerConverter();
    }

    @Test
    @DisplayName(" boolean 값 true를 1로 변환하는지 테스트")
    public void testConvertToDatabaseColumn_True() {
        Integer dbValue = converter.convertToDatabaseColumn(true);
        assertThat(dbValue).isEqualTo(1);
    }

    @Test
    @DisplayName("boolean 값 false를 0으로 변환하는지 테스트")
    public void testConvertToDatabaseColumn_False() {
        Integer dbValue = converter.convertToDatabaseColumn(false);
        assertThat(dbValue).isEqualTo(0);
    }

    @Test
    @DisplayName("null boolean 값이 0으로 변환되는지 테스트")
    public void testConvertToDatabaseColumn_Null() {
        Integer dbValue = converter.convertToDatabaseColumn(null);
        assertThat(dbValue).isEqualTo(0);
    }

    @Test
    @DisplayName("데이터베이스 값 1을 boolean true로 변환하는지 테스트")
    public void testConvertToEntityAttribute_One() {
        Boolean entityValue = converter.convertToEntityAttribute(1);
        assertThat(entityValue).isTrue();
    }

    @Test
    @DisplayName("데이터베이스 값 0을 boolean false로 변환하는지 테스트")
    public void testConvertToEntityAttribute_Zero() {
        Boolean entityValue = converter.convertToEntityAttribute(0);
        assertThat(entityValue).isFalse();
    }

    @Test
    @DisplayName("데이터베이스 값 null을 boolean false로 변환하는지 테스트")
    public void testConvertToEntityAttribute_Null() {
        Boolean entityValue = converter.convertToEntityAttribute(null);
        assertThat(entityValue).isFalse();
    }


}
