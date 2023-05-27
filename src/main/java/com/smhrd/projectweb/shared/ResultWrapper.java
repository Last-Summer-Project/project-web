package com.smhrd.projectweb.shared;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


/**
 * 반환하는 Json 값을 일정한 포멧에 맞게 Wrapping 하기 위해 사용합니다.
 *
 * @param <T> Any data type want to return is 'data' field
 */
@Data
public class ResultWrapper<T> {
    private static final String STATUS_OK = "ok";
    private static final String STATUS_FAIL = "fail";
    private static final String STATUS_ERROR = "error";
    private String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public T data;

    @JsonIgnore
    public int httpStatusCode;


    public ResultWrapper(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResultWrapper(int httpStatusCode, String status, String message, T data) {
        this.httpStatusCode = httpStatusCode;
        this.status = status;
        this.message = message;
        this.data = data;
    }


    public static <T> ResultWrapper<T> ok() {
        return new ResultWrapper<>(STATUS_OK, null, null);
    }

    public static <T> ResultWrapper<T> fail() {
        return new ResultWrapper<>(STATUS_FAIL, null, null);
    }

    public static <T> ResultWrapper<T> error() {
        return new ResultWrapper<>(STATUS_ERROR, null, null);
    }

    public static <T> ResultWrapper<T> ok(T data) {
        return new ResultWrapper<>(STATUS_OK, null, data);
    }

    public static <T> ResultWrapper<T> fail(T data) {
        return new ResultWrapper<>(400, STATUS_FAIL, null, data);
    }

    public static <T> ResultWrapper<T> error(T data) {
        return new ResultWrapper<>(500, STATUS_ERROR, null, data);
    }

    public static <T> ResultWrapper<T> ok(String message) {
        return new ResultWrapper<>(STATUS_OK, message, null);
    }

    public static <T> ResultWrapper<T> fail(String message) {
        return new ResultWrapper<>(400, STATUS_FAIL, message, null);
    }

    public static <T> ResultWrapper<T> error(String message) {
        return new ResultWrapper<>(500, STATUS_ERROR, message, null);
    }

    public static <T> ResultWrapper<T> fail(int httpStatusCode, String message) {
        return new ResultWrapper<>(httpStatusCode, STATUS_FAIL, message, null);
    }

    public static <T> ResultWrapper<T> error(int httpStatusCode, String message) {
        return new ResultWrapper<>(httpStatusCode, STATUS_ERROR, message, null);
    }

    public static <T> ResultWrapper<T> ok(String message, T data) {
        return new ResultWrapper<>(STATUS_OK, message, data);
    }

    public static <T> ResultWrapper<T> fail(String message, T data) {
        return new ResultWrapper<>(400, STATUS_FAIL, message, data);
    }

    public static <T> ResultWrapper<T> error(String message, T data) {
        return new ResultWrapper<>(500, STATUS_ERROR, message, data);
    }

    public static <T> ResultWrapper<T> fail(int httpStatusCode, String message, T data) {
        return new ResultWrapper<>(httpStatusCode, STATUS_FAIL, message, data);
    }

    public static <T> ResultWrapper<T> error(int httpStatusCode, String message, T data) {
        return new ResultWrapper<>(httpStatusCode, STATUS_ERROR, message, data);
    }

    public static <T> ResultWrapper<T> of(int httpStatusCode, String status, String message, T data) {
        return new ResultWrapper<>(httpStatusCode, status, message, data);
    }
}