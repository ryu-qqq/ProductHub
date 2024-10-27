package com.ryuqq.setof.domain.core.product.command;

import java.util.List;

public record ProductGroupCommandResponse(
   long productGroupId,
   List<Long> productIds
) {}
