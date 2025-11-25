package com.modernbank.parameter_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "service_error_logs")
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ServiceErrorLog {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private String id;

    private String userId;
    private String serviceName;
    private String errorCode;
    private String exceptionName;
    private String stackTrace;
    private String timestamp;
}