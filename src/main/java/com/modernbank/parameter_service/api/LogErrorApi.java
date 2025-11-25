package com.modernbank.parameter_service.api;

import com.modernbank.parameter_service.api.request.LogErrorRequest;
import com.modernbank.parameter_service.api.response.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface LogErrorApi {

    @PostMapping(path = "/error")
    BaseResponse logError(@RequestBody LogErrorRequest logErrorRequest);
}