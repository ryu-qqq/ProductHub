package com.ryuqq.setof.storage.db.core.data.utils;

import org.jeasy.random.api.Randomizer;

import java.util.Random;

class StringRandomizer implements Randomizer<String> {
    private final int length;

    public StringRandomizer(int length) {
        this.length = length;
    }

    @Override
    public String getRandomValue() {
        int leftLimit = 97; // 'a'
        int rightLimit = 122; // 'z'
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
