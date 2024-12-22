package com.ryuqq.setof.support.utils;
import org.apache.commons.text.similarity.LevenshteinDistance;


public class LevenshteinWordSimilarity implements WordSimilarity {

    @Override
    public double calculate(String s1, String s2) {
        LevenshteinDistance levenshtein = new LevenshteinDistance();
        int distance = levenshtein.apply(s1, s2);
        return 1.0 - (double) distance / Math.max(s1.length(), s2.length());
    }


}
