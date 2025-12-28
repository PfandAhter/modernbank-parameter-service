package com.modernbank.parameter_service.exceptions;

import com.modernbank.parameter_service.api.request.LogErrorRequest;
import com.modernbank.parameter_service.api.response.BaseResponse;
import com.modernbank.parameter_service.constant.HeaderKey;
import com.modernbank.parameter_service.entity.ErrorCode;
import com.modernbank.parameter_service.service.ErrorCodeService;
import com.modernbank.parameter_service.service.MapperService;
import com.modernbank.parameter_service.service.ServiceLogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final ErrorCodeService errorCodeService;

    private final ServiceLogService serviceLogService;

    private final MapperService mapperService;

    @ExceptionHandler(SecuriyException.class)
    public ResponseEntity<BaseResponse> handleSecurityException(SecuriyException exception, HttpServletRequest request) {
        log.error("SecurityException occurred: {}", exception.getMessage(), exception);
        ErrorCode errorCode = mapperService.map(errorCodeService.findErrorByCode(exception.getMessage()), ErrorCode.class);

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(createErrorResponseBody(exception, request, errorCode));
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<BaseResponse> handleNotFoundException(NotFoundException exception) {
        log.error("@@@@@@@@@@@ CRITICAL: NotFoundException occurred: {}", exception.getMessage(), exception);
        ErrorCode errorCode = getErrorCodeSafe();

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(new BaseResponse("FAILED", errorCode.getError(), errorCode.getDescription()));
    }

    private BaseResponse createErrorResponseBody(Exception exception, HttpServletRequest request, ErrorCode errorCodes) {
        logErrorToParameterService(exception, request, errorCodes);
        return new BaseResponse("FAILED", errorCodes.getError(), errorCodes.getDescription());
    }

    private void logErrorToParameterService(Exception exception, HttpServletRequest httpServletRequest, ErrorCode errorCode) {
        try {
            LogErrorRequest request = LogErrorRequest.builder()
                    .errorCode(exception.getMessage())
                    .serviceName("parameter-service")
                    .requestPath(httpServletRequest.getMethod() + " " + httpServletRequest.getRequestURI())
                    .traceId(httpServletRequest.getHeader(HeaderKey.CORRELATION_ID))
                    .timestamp(LocalDateTime.now())
                    .exceptionName(exception.getClass().getName())
                    .errorMessage(errorCode.getDescription())
                    .build();

            request.setUserId(httpServletRequest.getHeader(HeaderKey.USER_ID));
            serviceLogService.logError(request);
        } catch (Exception e) {
            log.error("Error log process failed " + e.getMessage());
        }
    }

    private ErrorCode getErrorCodeSafe() {
        return ErrorCode.builder()
                .id("SYSTEM_ERROR")
                .error("Sistem Hatası")
                .description("İşleminiz sırasında beklenmedik bir hata oluştu. Lütfen daha sonra tekrar deneyiniz.")
                .httpStatus(500)
                .build();
    }
}