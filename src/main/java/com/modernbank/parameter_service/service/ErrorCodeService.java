package com.modernbank.parameter_service.service;

import com.modernbank.parameter_service.model.ErrorCodeModel;

import java.util.List;

public interface ErrorCodeService {

    ErrorCodeModel findErrorByCode(String code);

    void refreshErrorCodesCache();

    List<ErrorCodeModel> getAllErrorCodes(String serviceName);
}