package com.ryuqq.setof.batch.core.brand;

import com.ryuqq.setof.domain.core.brand.Brand;
import com.ryuqq.setof.storage.db.index.brand.BrandDocument;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class BrandIndexingBatchProcessor implements ItemProcessor<Brand, BrandDocument> {

    @Override
    public BrandDocument process(Brand item) throws Exception {
        return new BrandDocument(
                String.valueOf(item.id()),
                item.brandName(),
                item.brandNameKr(),
                item.brandIconImageUrl(),
                item.displayYn()
        );
    }

}
