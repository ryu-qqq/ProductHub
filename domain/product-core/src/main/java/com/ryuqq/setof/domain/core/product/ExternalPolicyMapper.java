package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.domain.core.site.external.ExternalPolicy;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalPolicyDto;
import org.springframework.stereotype.Component;

@Component
public class ExternalPolicyMapper {

    public ExternalPolicy toDomain(ExternalPolicyDto dto){
        return new ExternalPolicy(
                dto.getSiteId(),
                dto.getPolicyId(),
                dto.getName(),
                dto.getDescription(),
                dto.isActiveYn()
        );
    }
}
