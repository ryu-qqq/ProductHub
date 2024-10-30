package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.storage.db.core.site.dto.SiteContextDto;
import org.springframework.stereotype.Component;

@Component
public class SiteContextMapper {

    public SiteContext toSiteContext(SiteContextDto siteContextDto, SiteProfile siteProfile){

        return new SiteContext(
                siteContextDto.getSiteId(),
                siteContextDto.getSiteName(),
                siteContextDto.getBaseUrl(),
                siteContextDto.getCountryCode(),
                siteContextDto.getSiteType(),
                siteProfile
        );
    }



}
