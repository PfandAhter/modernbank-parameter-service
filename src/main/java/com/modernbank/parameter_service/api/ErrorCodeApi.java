package com.modernbank.parameter_service.api;

import com.modernbank.parameter_service.api.response.ErrorCodeResponse;
import com.modernbank.parameter_service.api.response.GetAllErrorCodesResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ErrorCodeApi {

    @GetMapping(path = "/error-code")
    ErrorCodeResponse getErrorCode(@RequestParam("code") String code);

    @GetMapping(path = "/error-code/all")
    GetAllErrorCodesResponse getAllErrorCodes(@RequestParam("name") String serviceName);
}