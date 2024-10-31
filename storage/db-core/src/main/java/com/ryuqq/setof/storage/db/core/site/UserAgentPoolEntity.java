package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "USER_AGENT_POOL")
@Entity
public class UserAgentPoolEntity extends BaseEntity {

    @Column(name = "USER_AGENT_STRING", nullable = false, length = 255)
    private String userAgentString;

    protected UserAgentPoolEntity() {}

    public UserAgentPoolEntity(String userAgentString) {
        this.userAgentString = userAgentString;
    }

    public String getUserAgentString() {
        return userAgentString;
    }

}
