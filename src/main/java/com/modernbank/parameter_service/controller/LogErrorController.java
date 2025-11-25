package com.modernbank.parameter_service.controller;

import com.modernbank.parameter_service.api.LogErrorApi;
import com.modernbank.parameter_service.api.request.LogErrorRequest;
import com.modernbank.parameter_service.api.response.BaseResponse;
import com.modernbank.parameter_service.service.ServiceLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/log")
@RequiredArgsConstructor
@Slf4j
public class LogErrorController implements LogErrorApi {

    private final ServiceLogService serviceLogService;

    @Override
    public BaseResponse logError(LogErrorRequest logErrorRequest) {
        log.info("Received request to log error: {}", logErrorRequest);
        serviceLogService.logError(logErrorRequest);
        log.info("Error logged successfully for request: {}", logErrorRequest);

        return new BaseResponse("Error logged successfully");
    }
}