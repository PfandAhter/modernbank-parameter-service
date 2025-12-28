package com.modernbank.parameter_service.service;

public interface HeaderService {
    String extractUserEmail();

    String extractUserId();

    String extractUserRole();

    String extractCorrelationId();
}