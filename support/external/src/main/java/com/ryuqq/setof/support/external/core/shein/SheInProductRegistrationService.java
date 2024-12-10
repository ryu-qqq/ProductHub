package com.ryuqq.setof.support.external.core.shein;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.ExternalMallPreProductContext;
import com.ryuqq.setof.support.external.core.ExternalMallProductContext;
import com.ryuqq.setof.support.external.core.ExternalMallProductRegistrationService;
import com.ryuqq.setof.support.external.core.SyncResult;
import com.ryuqq.setof.support.external.core.shein.dto.SheInProductInsertResponseDto;
import org.springframework.stereotype.Service;

@Service
public class SheInProductRegistrationService implements ExternalMallProductRegistrationService {

    private final SheInClient sheInClient;
    private final SheInProductMapper sheInProductMapper;
    private final SheInProductResponseHandler sheInProductResponseHandler;

    public SheInProductRegistrationService(SheInClient sheInClient, SheInProductMapper sheInProductMapper, SheInProductResponseHandler sheInProductResponseHandler) {
        this.sheInClient = sheInClient;
        this.sheInProductMapper = sheInProductMapper;
        this.sheInProductResponseHandler = sheInProductResponseHandler;
    }


    @Override
    public SyncResult registration(ExternalMallPreProductContext context, ExternalMallProductContext externalMallProductContext) {
        return null;
    }

    @Override
    public SiteName getSiteName() {
        return SiteName.SHEIN;
    }

}
