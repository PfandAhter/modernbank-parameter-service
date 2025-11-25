package com.modernbank.parameter_service.api.response;

import com.modernbank.parameter_service.api.dto.ErrorCodesDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorCodeResponse {
    private ErrorCodesDTO errorCode;
}