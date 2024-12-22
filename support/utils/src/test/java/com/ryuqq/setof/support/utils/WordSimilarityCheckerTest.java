package com.ryuqq.setof.support.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordSimilarityCheckerTest {

    @Test
    public void testFindMostSimilar() {
        // Given
        WordSimilarityChecker checker = new WordSimilarityChecker();

        String target = "1968152NB-W(블랙화이트)/225";
        List<String> candidates = Arrays.asList("US4", "US5", "225", "230");

        // When
        String result = checker.findMostSimilar(target, candidates);

        // Then
        assertEquals("225", result);
    }


}