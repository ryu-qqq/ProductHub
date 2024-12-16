package com.ryuqq.setof.support.external.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SheInProductInsertResponseDto(
        @JsonProperty("success")
        boolean success,
        @JsonProperty("spu_name")
        String spuName,
        @JsonProperty("pre_valid_result")
        List<PreValidResult> preValidResult,
        @JsonProperty("mcc_valid_result")
        List<MccValidResult> mccValidResults,
        @JsonProperty("skc_list")
        List<SkuList> skuList
) {

        public record PreValidResult(
                String form,
                List<String> messages,
                String module
        ){

                public String getErrorMessage(){
                        StringBuilder sb = new StringBuilder();
                        sb.append(String.format("form : %s \n", form));
                        messages.forEach(s -> sb.append(s).append("\n"));
                        sb.append(String.format("module : %s", module));
                        return sb.toString();
                }
        }

        public record MccValidResult(
                String message,
                Integer type
        ){}


        public record SkuList(
                @JsonProperty("sku_code")
                String skuCode,
                @JsonProperty("supplier_sku")
                String supplierSku
        ){}

}
