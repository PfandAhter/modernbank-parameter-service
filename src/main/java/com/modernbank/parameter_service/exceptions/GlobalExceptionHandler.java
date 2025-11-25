package com.modernbank.parameter_service.exceptions;

import com.modernbank.parameter_service.api.dto.ErrorCodesDTO;
import com.modernbank.parameter_service.api.response.ErrorCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorCodeResponse> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createErrorResponseBody(exception.getErrorCode()));
    }

    private ErrorCodeResponse createErrorResponseBody(String errorCode){
        return new ErrorCodeResponse(ErrorCodesDTO.builder()
                .id(errorCode)
                .error("Not Found")
                .description("The requested resource was not found")
                .build());
    }
}