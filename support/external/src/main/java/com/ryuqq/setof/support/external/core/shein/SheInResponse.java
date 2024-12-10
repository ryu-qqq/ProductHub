package com.ryuqq.setof.support.external.core.shein;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SheInResponse<T>{
        private String code;
        @JsonProperty("msg")
        private String message;
        private Info<T> info;


        public String getCode() {
                return code;
        }

        public String getMessage() {
                return message;
        }

        public Info<T> getInfo() {
                return info;
        }
}
