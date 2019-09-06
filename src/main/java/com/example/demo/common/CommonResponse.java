package com.example.demo.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResponse<T> {

    private String code;

    private String message;

    private String cause;

    private T body;

    /**
     * Response with error code,message,cause and response body.
     */
    public static <T> CommonResponse response(String code, String message, String cause, T body) {

        return new CommonResponse(code, message, cause, body);
    }

    /**
     * Response with success code,message,cause and response body.
     */
    public static <T> CommonResponse ok(T body) {

        return new CommonResponse("200", "Success", "", body);
    }

    /**
     * Constructor.
     */
    public CommonResponse(String code, String message, String cause, T body) {
        this.code = code;
        this.message = message;
        this.cause = cause;
        this.body = body;
    }

    /**
     * Non-parameter constructor.
     */
    public CommonResponse() {
    }

    /**
     * Constructor.
     */
    public CommonResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
