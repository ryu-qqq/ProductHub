package com.ryuqq.setof.storage.db.index;

import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;

import java.util.List;
import java.util.function.Function;

public class BulkOperationFactory {

    public static <T> List<BulkOperation> createOperations(List<T> documents, Function<T, String> idExtractor) {
        return documents.stream()
                .map(doc -> BulkOperation.of(op -> op
                        .index(idx -> idx
                                .id(idExtractor.apply(doc))
                                .document(doc)
                        )
                ))
                .toList();
    }

}
