package com.modernbank.parameter_service.entity;

import com.modernbank.parameter_service.model.enums.RequestStatus;
import com.modernbank.parameter_service.model.enums.RequestType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "support_request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupportRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tracking_number", unique = true, nullable = false, length = 20)
    private String trackingNumber;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "email", length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_type", nullable = false)
    private RequestType requestType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RequestStatus status;

    @Column(name = "error_code", length = 10)
    private String errorCode;

    @Column(name = "title", length = 200)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "arguments", columnDefinition = "TEXT")
    private String arguments; // JSON formatında dinamik argümanlar

    @Column(name = "source_service", length = 50)
    private String sourceService;

    @Column(name = "endpoint", length = 200)
    private String endpoint;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "correlation_id", length = 100)
    private String correlationId;

    @Column(name = "admin_notes", columnDefinition = "TEXT")
    private String adminNotes;

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;

    @Column(name = "resolved_by")
    private String resolvedBy;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}