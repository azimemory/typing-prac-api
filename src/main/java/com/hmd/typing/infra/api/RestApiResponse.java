package com.hmd.typing.infra.api;

import java.util.Map;

import lombok.Data;

@Data
public class RestApiResponse {

    private boolean success;
    private Map<String, Object> data;

    public static RestApiResponse createJson(boolean success, Map<String, Object> data) {
        RestApiResponse restApiResponse = new RestApiResponse();
        restApiResponse.setSuccess(success);
        restApiResponse.setData(data);
        return restApiResponse;
    }
}