package com.ryuqq.setof.storage.db.index.site;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@Document(indexName = "external_requests")
public class ExternalRequestDocument {

    @Id
    private String transactionId;

    private String requestType;
    private String siteId;
    private String entityType;
    private Long entityId;
    private String status;
    private String requestBody;
    private String responseBody;
    private LocalDateTime insertTime;
    private LocalDateTime updateTime;

}
