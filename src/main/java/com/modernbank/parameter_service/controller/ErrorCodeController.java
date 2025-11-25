package com.modernbank.parameter_service.controller;

import com.modernbank.parameter_service.api.ErrorCodeApi;
import com.modernbank.parameter_service.api.dto.ErrorCodesDTO;
import com.modernbank.parameter_service.api.response.ErrorCodeResponse;
import com.modernbank.parameter_service.api.response.GetAllErrorCodesResponse;
import com.modernbank.parameter_service.service.ErrorCodeService;
import com.modernbank.parameter_service.service.MapperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/error-codes")
@Slf4j
public class ErrorCodeController implements ErrorCodeApi {

    private final MapperService mapperService;

    private final ErrorCodeService errorCodeService;

    @Override
    public ErrorCodeResponse getErrorCode(String code) {
        log.info("Received request to get error code: {}", code);
        ErrorCodeResponse response = new ErrorCodeResponse(
                mapperService.map(errorCodeService.findErrorByCode(code), ErrorCodesDTO.class));
        log.info("Returning response for error code: {}", code);
        return response;
    }

    @Override
    public GetAllErrorCodesResponse getAllErrorCodes(String serviceName) {
        log.info("Received request to get all error codes for service: {}", serviceName);
        GetAllErrorCodesResponse response = new GetAllErrorCodesResponse(
                mapperService.map(errorCodeService.getAllErrorCodes(serviceName), ErrorCodesDTO.class));
        log.info("Returning all error codes for service: {}", serviceName);
        return response;
    }
}