package com.modernbank.parameter_service.controller;

import com.modernbank.parameter_service.api.CacheApi;
import com.modernbank.parameter_service.api.response.BaseResponse;
import com.modernbank.parameter_service.service.ErrorCodeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cache")
@Slf4j
public class CacheController implements CacheApi {

    private final ErrorCodeService errorCodeService;

    @Override
    public BaseResponse refreshCache(HttpServletRequest request) {
        errorCodeService.refreshErrorCodesCache();
        return new BaseResponse("Parameter Service Error Code list cache refreshed successfully.");
    }
}