package com.ryuqq.setof.domain.core.site.external;

import java.util.List;

public record ExternalMallSyncResponseContext(
        List<ExternalProductGroupUpdateCommand> externalProductGroupUpdateCommands,
        List<ExternalRequestCommand> externalRequestCommands,
        List<ExternalProductImageCommand> externalProductImageCommands
) {
}
