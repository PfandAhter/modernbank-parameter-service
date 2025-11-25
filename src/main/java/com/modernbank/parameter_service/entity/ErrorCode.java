package com.modernbank.parameter_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Table(name = "error_code")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ErrorCode implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "error")
    private String error;

    @Column(name = "description")
    private String description;
}