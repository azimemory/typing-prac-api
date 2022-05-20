package com.hmd.typing.infra.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmd.typing.infra.code.ErrorCode;
import com.hmd.typing.infra.exception.HandlableException;

public class RestApiController {

    final ObjectMapper objectMapper;

    public RestApiController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // Case 1. Fail (Spring Security Exception)

    // Case 2. Fail (Valid Error)
    //  return
    //      success: false,
    //      data: {
    //          getField(): getDefaultMessage(),
    //          ...
    //      }
    @SuppressWarnings("serial")
	protected ResponseEntity<String> createFailRestResponse(BindingResult result) {
        Map<String, Object> data = new HashMap<>() {{
            put("error", result);
        }};
        RestApiResponse restApiResponse = RestApiResponse.createJson(false, data);
        return convertToResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, restApiResponse);
    }

    protected ResponseEntity<String> createFailRestResponse(Map<String, Object> data) {
        RestApiResponse restApiResponse = RestApiResponse.createJson(false, data);
        return convertToResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, restApiResponse);
    }

    // Case 3. Fail (Service Method Exception)

    // Case 4. Success
    //  return
    //      success: true,
    //      data: {
    //          account: {
    //              email: "hgd@gmail.com",
    //              name: "홍길동",
    //              ...
    //          },
    //          ...
    //      }
    protected ResponseEntity<String> createRestResponse(Map<String, Object> data) {
        RestApiResponse restApiResponse = RestApiResponse.createJson(true, data);
        return convertToResponseEntity(HttpStatus.OK, restApiResponse);
    }

    private ResponseEntity<String> convertToResponseEntity(HttpStatus status, RestApiResponse restApiResponse) {
        String responseBody;
        try {
            responseBody = objectMapper.writeValueAsString(restApiResponse);
        }
        catch (JsonProcessingException exception) {
            throw new HandlableException(ErrorCode.JSON_PROCESS_FAIL, exception);
        }
        return ResponseEntity.status(status).body(responseBody);
    }
}
