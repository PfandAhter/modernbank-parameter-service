package com.modernbank.parameter_service.config;

import com.modernbank.parameter_service.entity.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfiguration {


    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisHost, 6379);
    }

    @Bean
    public RedisTemplate<String, ErrorCode> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, ErrorCode> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        Jackson2JsonRedisSerializer<ErrorCode> jsonSerializer = new Jackson2JsonRedisSerializer<>(ErrorCode.class);
        template.setValueSerializer(jsonSerializer);
        return template;
    }
}