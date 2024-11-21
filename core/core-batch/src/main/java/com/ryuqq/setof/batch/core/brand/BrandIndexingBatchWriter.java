package com.ryuqq.setof.batch.core.brand;

import com.ryuqq.setof.storage.db.index.brand.BrandDocument;
import com.ryuqq.setof.storage.db.index.brand.BrandDocumentCommandService;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BrandIndexingBatchWriter implements ItemWriter<BrandDocument> {


    private final BrandDocumentCommandService brandDocumentCommandService;

    public BrandIndexingBatchWriter(BrandDocumentCommandService brandDocumentCommandService) {
        this.brandDocumentCommandService = brandDocumentCommandService;
    }

    @Override
    public void write(Chunk<? extends BrandDocument> chunk) {
        brandDocumentCommandService.inserts((List<BrandDocument>) chunk.getItems());
    }

}
