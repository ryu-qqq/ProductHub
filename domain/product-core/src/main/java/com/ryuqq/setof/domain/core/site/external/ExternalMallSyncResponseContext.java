package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.db.core.site.external.ExternalProductUpdateCommand;

import java.util.List;

public record ExternalMallSyncResponseContext(
        List<ExternalProductGroupUpdateCommand> externalProductGroupUpdateCommands,
        List<ExternalProductUpdateCommand> externalProductUpdateCommands,
        List<ExternalRequestCommand> externalRequestCommands,
        List<ExternalProductImageCommand> externalProductImageCommands
) {
}
