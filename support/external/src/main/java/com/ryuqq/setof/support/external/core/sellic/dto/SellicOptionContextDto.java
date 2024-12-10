package com.ryuqq.setof.support.external.core.sellic.dto;

import java.util.List;

public record SellicOptionContextDto(
         List<SellicOptionDto> options,
         String optionName1,
         String optionName2
) {}
