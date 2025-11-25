package com.modernbank.parameter_service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ServiceErrorLogModel {
    private String userId;
    private String serviceName;
    private String errorCode;
    private String exceptionName;
    private String timestamp;
    private String stackTrace;
}