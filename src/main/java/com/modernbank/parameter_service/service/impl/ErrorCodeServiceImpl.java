package com.modernbank.parameter_service.service.impl;

import com.modernbank.parameter_service.api.response.GetAllErrorCodesResponse;
import com.modernbank.parameter_service.entity.ErrorCode;
import com.modernbank.parameter_service.exceptions.NotFoundException;
import com.modernbank.parameter_service.model.ErrorCodeModel;
import com.modernbank.parameter_service.repository.ErrorCodeRepository;
import com.modernbank.parameter_service.service.ErrorCodeService;
import com.modernbank.parameter_service.service.MapperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

import static com.modernbank.parameter_service.constant.ErrorCodeConstant.ERROR_CODE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class ErrorCodeServiceImpl implements ErrorCodeService {

    private final ErrorCodeRepository errorCodeRepository;

    private final RedisTemplate<String, ErrorCode> redisTemplate;

    private static final String ERROR_CODE_CACHE = "ERROR_CODE_CACHE";

    private final MapperService mapperService;

    @Override
    public ErrorCodeModel findErrorByCode(String code) {
        String cacheKey = ERROR_CODE_CACHE + ":" + code;

        ErrorCode cachedErrorCode = redisTemplate.opsForValue().get(cacheKey);
        if(cachedErrorCode != null) {
            return mapperService.map(cachedErrorCode, ErrorCodeModel.class);
        }

        ErrorCode errorCode = errorCodeRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException(ERROR_CODE_NOT_FOUND,code));

        redisTemplate.opsForValue().set(cacheKey, errorCode, Duration.ofHours(1));
        return mapperService.map(errorCode, ErrorCodeModel.class);
    }

    @Override
    public void refreshErrorCodesCache(){
        errorCodeRepository.findAll().forEach(errorCode -> {
            String key = ERROR_CODE_CACHE + ":" + errorCode.getError();
            redisTemplate.opsForValue().setIfAbsent(key, errorCode, Duration.ofHours(1));
        });
        redisTemplate.opsForValue().set(ERROR_CODE_CACHE + ":ALL", new ErrorCode(), Duration.ofHours(1));
    }

    @Override
    public List<ErrorCodeModel> getAllErrorCodes(String serviceName){
        log.info("getAllErrorCodes called by service: {}", serviceName);

        List<ErrorCode> errorCodes = errorCodeRepository.findAll();
        return errorCodes.stream()
                .map(errorCode -> mapperService.map(errorCode, ErrorCodeModel.class))
                .toList();
    }
}