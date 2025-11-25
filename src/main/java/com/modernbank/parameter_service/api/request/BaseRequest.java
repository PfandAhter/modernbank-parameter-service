package com.modernbank.parameter_service.api.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseRequest {

    @JsonIgnore
    private LocalDateTime requestTime = LocalDateTime.now();

    @JsonIgnore
    private String userId;
}