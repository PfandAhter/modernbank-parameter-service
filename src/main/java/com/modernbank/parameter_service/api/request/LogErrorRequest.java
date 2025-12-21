package com.modernbank.parameter_service.api.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogErrorRequest extends BaseRequest {
    private String traceId;

    private String requestPath;

    private String exceptionName;

    private String serviceName;

    private String errorCode;

    private String errorMessage;

    private String stackTrace;

    private LocalDateTime timestamp;
}