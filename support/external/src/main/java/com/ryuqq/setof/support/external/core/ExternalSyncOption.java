package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.OptionName;

public record ExternalSyncOption(
        OptionName optionName,
        String optionValue
) {}

