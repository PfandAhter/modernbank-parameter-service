package com.modernbank.parameter_service.aspect;

import com.modernbank.parameter_service.api.request.BaseRequest;
import com.modernbank.parameter_service.service.HeaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class BeforeControllerAspect {

    private final HeaderService headerService;

    @Before(value = "execution(* com.modernbank.parameter_service.controller.ErrorCodeController.*(..))")
    public void setTokenBeforeController(JoinPoint joinPoint){
        Object[] parameters = joinPoint.getArgs();
        for(Object param : parameters){
            if(param instanceof BaseRequest baseRequest){
                String userId = headerService.extractUserId();
                baseRequest.setUserId(userId);
            }
        }
    }
}