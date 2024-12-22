package com.ryuqq.setof.support.utils;

import java.util.List;

public class WordSimilarityChecker {

    private final WordSimilarity wordSimilarity;


    public WordSimilarityChecker() {
        this.wordSimilarity = new LevenshteinWordSimilarity();
    }

    public WordSimilarityChecker(WordSimilarity wordSimilarity) {
        this.wordSimilarity = wordSimilarity;
    }

    // 가장 유사도가 높은 단어를 반환하는 메서드
    public String findMostSimilar(String target, List<String> candidates) {
        String bestMatch = null;
        double highestScore = -1.0;

        for (String candidate : candidates) {
            double score = wordSimilarity.calculate(target, candidate);
            if (score > highestScore) {
                highestScore = score;
                bestMatch = candidate;
            }
        }
        return bestMatch;
    }

}
