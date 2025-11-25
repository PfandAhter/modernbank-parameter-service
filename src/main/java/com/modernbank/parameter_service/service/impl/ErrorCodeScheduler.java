package com.modernbank.parameter_service.service.impl;

import com.modernbank.parameter_service.service.ErrorCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ErrorCodeScheduler {

    private final ErrorCodeService errorCacheService;

    @Scheduled(cron = "${error.codes.cron:0 0 * * * *}") // Default value is every hour at minute 0
    public void refreshErrorCodesCache(){
        log.info("Refreshing error codes cache");
        try{
            errorCacheService.refreshErrorCodesCache();
            log.info("Error codes cache refreshed successfully");
        }catch (Exception e){
            log.error("Error occurred while refreshing error codes cache", e);
        }
    }
}