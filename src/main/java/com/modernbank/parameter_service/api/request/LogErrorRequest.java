package com.modernbank.parameter_service.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LogErrorRequest extends BaseRequest{
    private String serviceName;
    private String errorCode;
    private String errorMessage;
    private String timestamp;
    private String stackTrace;
}