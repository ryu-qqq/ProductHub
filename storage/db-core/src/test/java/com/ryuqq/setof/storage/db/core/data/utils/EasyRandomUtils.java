package com.ryuqq.setof.storage.db.core.data.utils;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.randomizers.range.LongRangeRandomizer;
import org.jeasy.random.randomizers.range.BigDecimalRangeRandomizer;
import org.jeasy.random.randomizers.range.IntegerRangeRandomizer;
import java.math.BigDecimal;
import java.util.Map;

import static org.jeasy.random.FieldPredicates.named;

public class EasyRandomUtils {
    private static final EasyRandom easyRandom;

    static {
        EasyRandomParameters parameters = new EasyRandomParameters()
                .excludeField(named("id"))
                .objectFactory(new RecordRandomizerRegistry())
                .randomize(Long.class, new LongRangeRandomizer(1L, 100000000L))
                .randomize(String.class, new StringRandomizer(10))
                .randomize(BigDecimal.class, new BigDecimalRangeRandomizer(0.0, 1000000000.0))
                .randomize(Integer.class, new IntegerRangeRandomizer(0, 100))
                .collectionSizeRange(1, 2);

        easyRandom = new EasyRandom(parameters);
    }


    public static EasyRandom getInstance() {
        return easyRandom;
    }



    public static EasyRandom getInstance(Map<String, Object> values) {
        EasyRandomParameters parameters = new EasyRandomParameters()
                .objectFactory(new RecordRandomizerRegistry())
                .randomize(Long.class, new LongRangeRandomizer(1L, 100000000L))
                .randomize(String.class, new StringRandomizer(10))
                .collectionSizeRange(1, 2);

        for(Map.Entry<String, Object> entry : values.entrySet()){
            parameters.randomize(field -> field.getName().equals(entry.getKey()), entry::getValue);
        }

        return new EasyRandom(parameters);
    }


    public static EasyRandom getInstanceWithNoId(Map<String, Object> values) {
        EasyRandomParameters parameters = new EasyRandomParameters()
                .excludeField(named("id"))
                .objectFactory(new RecordRandomizerRegistry())
                .randomize(Long.class, new LongRangeRandomizer(1L, 100000000L))
                .randomize(String.class, new StringRandomizer(15))
                .collectionSizeRange(1, 2);

        for(Map.Entry<String, Object> entry : values.entrySet()){
            parameters.randomize(field -> field.getName().equals(entry.getKey()), entry::getValue);
        }

        return new EasyRandom(parameters);
    }
}
