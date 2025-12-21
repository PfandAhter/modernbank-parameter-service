package com.modernbank.parameter_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "service_error_logs", indexes = {
        @Index(name = "idx_trace_id", columnList = "traceId"),
        @Index(name = "idx_created_at", columnList = "createdAt")
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceErrorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "trace_id")
    private String traceId;

    private String userId;

    private String serviceName;

    private String errorCode;

    @Column(name = "error_message", length = 1000)
    private String errorMessage;

    private String exceptionName;

    @Column(name = "stack_trace", columnDefinition = "TEXT")
    private String stackTrace;

    @Column(name = "request_path")
    private String requestPath;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}