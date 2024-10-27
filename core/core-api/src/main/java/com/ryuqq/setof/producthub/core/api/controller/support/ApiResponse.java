package com.ryuqq.setof.producthub.core.api.controller.support;

public class ApiResponse<T> {

    private T data;
    private Response response;


    public ApiResponse(T data, Response response) {
        this.data = data;
        this.response = response;
    }

    public static <T> ApiResponse<T> success(T apiResult) {
        Response response = new Response(200, "success");
        return new ApiResponse<>(apiResult, response);
    }

    public static ApiResponse<?> error(ErrorMessage errorMessage) {
        return new ApiResponse<>(null, errorMessage.toErrorResponse());
    }


}
