package com.modernbank.parameter_service.service.impl;

import com.modernbank.parameter_service.api.request.LogErrorRequest;
import com.modernbank.parameter_service.entity.ServiceErrorLog;
import com.modernbank.parameter_service.repository.ServiceErrorLogRepository;
import com.modernbank.parameter_service.service.MapperService;
import com.modernbank.parameter_service.service.ServiceLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceLogServiceImpl implements ServiceLogService {

    private final MapperService mapperService;

    private final ServiceErrorLogRepository serviceErrorLogRepository;

    @Override
    public void logError(LogErrorRequest request) {
        log.info("Logging error traceId {} : Service: {}, ErrorCode: {}, Message: {}",request.getTraceId(), request.getServiceName(),
                request.getErrorCode(), request.getErrorMessage());
        ServiceErrorLog serviceErrorLog = mapperService.map(request, ServiceErrorLog.class);
        serviceErrorLog.setCreatedAt(request.getTimestamp());

        serviceErrorLogRepository.save(serviceErrorLog);
    }
}