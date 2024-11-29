package com.ryuqq.setof.batch.core.brand;

import com.ryuqq.setof.storage.db.index.brand.BrandDocument;
import com.ryuqq.setof.storage.db.index.brand.BrandDocumentIndexingRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BrandIndexingBatchWriter implements ItemWriter<BrandDocument> {


    private final BrandDocumentIndexingRepository brandDocumentIndexingRepository;

    public BrandIndexingBatchWriter(BrandDocumentIndexingRepository brandDocumentIndexingRepository) {
        this.brandDocumentIndexingRepository = brandDocumentIndexingRepository;
    }

    @Override
    public void write(Chunk<? extends BrandDocument> chunk) {
        brandDocumentIndexingRepository.insertAllBrandDocument((List<BrandDocument>) chunk.getItems());
    }

}
