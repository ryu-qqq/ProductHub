package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.enums.core.ProductDataType;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.*;

@Table(name = "EXTERNAL_PROCESSING_RESULT")
@Entity
public class ExternalProcessingResultEntity extends BaseEntity {


    @Column(name = "PRODUCT_GROUP_ID", nullable = false)
    private long productGroupId;

    @Column(name = "PRODUCT_DATA_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductDataType productDataType;

    @Column(name = "RESULT", nullable = false)
    private String result;


    protected ExternalProcessingResultEntity() {}

    public ExternalProcessingResultEntity(long productGroupId, ProductDataType productDataType, String result) {
        this.productGroupId = productGroupId;
        this.productDataType = productDataType;
        this.result = result;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public ProductDataType getProductDataType() {
        return productDataType;
    }

    public String getResult() {
        return result;
    }
}
