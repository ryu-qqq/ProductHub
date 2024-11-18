package com.ryuqq.setof.storage.db.index.product;

import com.ryuqq.setof.enums.core.OptionName;

public record OptionDocument(OptionName optionName, String optionValue) {
}