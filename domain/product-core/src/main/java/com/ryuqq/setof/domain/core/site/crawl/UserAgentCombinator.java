package com.ryuqq.setof.domain.core.site.crawl;


import com.ryuqq.setof.db.core.site.HeaderConfigurationQueryDslRepository;
import com.ryuqq.setof.db.core.site.HeaderQueryDslRepository;
import com.ryuqq.setof.db.core.site.UserAgentQueryDslRepository;
import com.ryuqq.setof.db.core.site.dto.HeaderConfigurationDto;
import com.ryuqq.setof.db.core.site.dto.HeaderDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserAgentCombinator {

    private final HeaderQueryDslRepository headerQueryDslRepository;
    private final HeaderConfigurationQueryDslRepository headerConfigurationQueryDslRepository;
    private final UserAgentQueryDslRepository userAgentQueryDslRepository;


    public UserAgentCombinator(HeaderQueryDslRepository headerQueryDslRepository, HeaderConfigurationQueryDslRepository headerConfigurationQueryDslRepository, UserAgentQueryDslRepository userAgentQueryDslRepository) {
        this.headerQueryDslRepository = headerQueryDslRepository;
        this.headerConfigurationQueryDslRepository = headerConfigurationQueryDslRepository;
        this.userAgentQueryDslRepository = userAgentQueryDslRepository;
    }

    public Map<String, String> generateRandomHeaders(String configurationName) {
        Map<String, String> headers = new HashMap<>();

        List<HeaderConfigurationDto> configurations = headerConfigurationQueryDslRepository.fetchByConfigurationName(configurationName);
        configurations.forEach(config -> headers.put(config.getHeaderName(), config.getHeaderValue()));

        List<HeaderDto> randomHeaders = headerQueryDslRepository.fetchHeader(5);
        randomHeaders.forEach(header -> headers.putIfAbsent(header.getHeaderName(), header.getHeaderValue()));

        if (!headers.containsKey("User-Agent")) {
            userAgentQueryDslRepository.fetchRandomUserAgent().ifPresent(userAgent ->
                    headers.put("User-Agent", userAgent.getUserAgent())
            );
        }

        return headers;
    }


}
