package com.ryuqq.setof.support.external.core.shein;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SheInResponse<T>{
        private final String code;

        @JsonProperty("msg")
        private final String message;

        private final T info;

        public SheInResponse(String code, String message, T info) {
                this.code = code;
                this.message = message;
                this.info = info;
        }

        public String getCode() {
                return code;
        }

        public String getMessage() {
                return message;
        }

        public T getInfo() {
                return info;
        }


}
