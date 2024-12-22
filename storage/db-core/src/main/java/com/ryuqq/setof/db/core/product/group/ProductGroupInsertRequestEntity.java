package com.ryuqq.setof.storage.db.core.product.group;


import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "PRODUCT_GROUP_INSERT_REQUEST")
@Entity
public class ProductGroupInsertRequestEntity extends BaseEntity {

    @Column(name = "PRODUCT_GROUP_ID", nullable = false)
    private long productGroupId;


    @Column(name = "REQUEST_BODY", nullable = false)
    private String requestBody;

    protected ProductGroupInsertRequestEntity() {}

    public ProductGroupInsertRequestEntity(long productGroupId, String requestBody) {
        this.productGroupId = productGroupId;
        this.requestBody = requestBody;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public String getRequestBody() {
        return requestBody;
    }
}
