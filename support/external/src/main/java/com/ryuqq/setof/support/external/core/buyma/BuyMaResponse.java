package com.ryuqq.setof.support.external.core.buyma;

import java.time.LocalDateTime;
import java.util.Map;

public class BuyMaResponse<T> {

    private T t;

    public T getT() {
        return t;
    }

    public static class Success {
        private LocalDateTime requestReceivedAt;
        private String requestUid;

        public Success() {}

        public Success(LocalDateTime requestReceivedAt, String requestUid) {
            this.requestReceivedAt = requestReceivedAt;
            this.requestUid = requestUid;
        }

        public String getRequestUid() {
            return requestUid;
        }

        @Override
        public String toString() {
            return "Success{" +
                    "requestReceivedAt=" + requestReceivedAt +
                    ", requestUid='" + requestUid + '\'' +
                    '}';
        }
    }

    public static class Failure {
        private Map<String, String[]> errors;

        public Failure() {}

        public Failure(Map<String, String[]> errors) {
            this.errors = errors;
        }

        public Map<String, String[]> getErrors() {
            return errors;
        }

        @Override
        public String toString() {
            return "Failure{" +
                    "errors=" + errors +
                    '}';
        }
    }
}
