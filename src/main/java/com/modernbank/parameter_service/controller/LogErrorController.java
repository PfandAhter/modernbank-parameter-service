package com.modernbank.parameter_service.controller;

import com.modernbank.parameter_service.api.LogErrorApi;
import com.modernbank.parameter_service.api.request.LogErrorRequest;
import com.modernbank.parameter_service.api.response.BaseResponse;
import com.modernbank.parameter_service.service.ServiceLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/log")
@RequiredArgsConstructor
public class LogErrorController implements LogErrorApi {

    private final ServiceLogService serviceLogService;

    @Override
    public BaseResponse logError(LogErrorRequest logErrorRequest) {
        serviceLogService.logError(logErrorRequest);

        return new BaseResponse("Error logged successfully");
    }
}