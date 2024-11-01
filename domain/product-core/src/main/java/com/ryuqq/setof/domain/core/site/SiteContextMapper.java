package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.storage.db.core.site.dto.SiteContextDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SiteContextMapper {

    public SiteContext toSiteContext(SiteContextDto siteContextDto, List<SiteProfile> siteProfiles){
        return new SiteContext(
                siteContextDto.getSiteId(),
                siteContextDto.getSiteName(),
                siteContextDto.getBaseUrl(),
                siteContextDto.getCountryCode(),
                siteContextDto.getSiteType(),
                siteProfiles
        );
    }



}
