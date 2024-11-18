package com.ryuqq.setof.api.core.controller.support;

public class Response {

    private int status;
    private String message;

    protected Response() {}

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
