package com.modernbank.parameter_service.controller;

import com.modernbank.parameter_service.api.ErrorCodeApi;
import com.modernbank.parameter_service.api.dto.ErrorCodesDTO;
import com.modernbank.parameter_service.api.response.ErrorCodeResponse;
import com.modernbank.parameter_service.api.response.GetAllErrorCodesResponse;
import com.modernbank.parameter_service.service.ErrorCodeService;
import com.modernbank.parameter_service.service.MapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/error-codes")
public class ErrorCodeController implements ErrorCodeApi {

    private final MapperService mapperService;

    private final ErrorCodeService errorCodeService;

    @Override
    public ErrorCodeResponse getErrorCode(String code) {
        return new ErrorCodeResponse(mapperService.map(errorCodeService.findErrorByCode(code), ErrorCodesDTO.class));
    }

    @Override
    public GetAllErrorCodesResponse getAllErrorCodes(String serviceName) {
        return new GetAllErrorCodesResponse(mapperService.map(errorCodeService.getAllErrorCodes(serviceName), ErrorCodesDTO.class));
    }
}