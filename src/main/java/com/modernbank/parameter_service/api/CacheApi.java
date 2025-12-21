package com.modernbank.parameter_service.api;

import com.modernbank.parameter_service.api.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;

public interface CacheApi {

    @GetMapping(path = "/refresh")
    BaseResponse refreshCache(HttpServletRequest request);
}