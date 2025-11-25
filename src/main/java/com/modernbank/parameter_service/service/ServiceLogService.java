package com.modernbank.parameter_service.service;

import com.modernbank.parameter_service.api.request.LogErrorRequest;

public interface ServiceLogService {
    void logError(LogErrorRequest request);
}