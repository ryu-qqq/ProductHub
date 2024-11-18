package com.ryuqq.setof.storage.db.index.product;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(indexName = "product-group-change-log")
public class ProductGroupChangeLogDocument {

    @Id
    private String id;
    private final Long productGroupId;
    private final String changeType;
    private final LocalDateTime updateTime;
    private final String status;
    private final List<FieldChangeLog> changedFields;

    public ProductGroupChangeLogDocument(String id, Long productGroupId, String changeType, LocalDateTime updateTime, String status, List<FieldChangeLog> changedFields) {
        this.id = id;
        this.productGroupId = productGroupId;
        this.changeType = changeType;
        this.updateTime = updateTime;
        this.status = status;
        this.changedFields = changedFields;
    }

    public String getId() {
        return id;
    }

    public Long getProductGroupId() {
        return productGroupId;
    }

    public String getChangeType() {
        return changeType;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public String getStatus() {
        return status;
    }

    public List<FieldChangeLog> getChangedFields() {
        return changedFields;
    }

    public static class FieldChangeLog {
        private final String fieldName;
        private final String oldValue;
        private final String newValue;


        public FieldChangeLog(String fieldName, String oldValue, String newValue) {
            this.fieldName = fieldName;
            this.oldValue = oldValue;
            this.newValue = newValue;
        }

        public String getFieldName() {
            return fieldName;
        }

        public String getOldValue() {
            return oldValue;
        }

        public String getNewValue() {
            return newValue;
        }
    }
}
