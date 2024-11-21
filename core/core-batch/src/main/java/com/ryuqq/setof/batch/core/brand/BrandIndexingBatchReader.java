package com.ryuqq.setof.batch.core.brand;


import com.ryuqq.setof.domain.core.brand.BrandFilter;
import com.ryuqq.setof.domain.core.brand.BrandFinder;
import com.ryuqq.setof.domain.core.brand.BrandQueryService;
import com.ryuqq.setof.domain.core.brand.BrandRecord;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class BrandIndexingBatchReader implements ItemReader<BrandRecord> {


    private final BrandQueryService brandQueryService;
    private final int pageSize;
    private Long cursorId = null;
    private Iterator<BrandRecord> currentBatchIterator;

    public BrandIndexingBatchReader(BrandFinder brandQueryService) {
        this.brandQueryService = brandQueryService;
        this.pageSize = 20;
    }

    @Override
    public BrandRecord read() {
        if (currentBatchIterator == null || !currentBatchIterator.hasNext()) {
            List<BrandRecord> currentBatch = fetchNextBatch();
            if (currentBatch.isEmpty()) {
                return null;
            }
            currentBatchIterator = currentBatch.iterator();
        }

        return currentBatchIterator.next();
    }

    private List<BrandRecord> fetchNextBatch() {
        BrandFilter filter = new BrandFilter(null, cursorId, "", pageSize);
        List<BrandRecord> brands = brandQueryService.findBrands(filter);

        if (!brands.isEmpty()) {
            cursorId = brands.getLast().id();
        }

        return brands;
    }


}
