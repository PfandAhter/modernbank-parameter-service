package com.modernbank.parameter_service.api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorCodesDTO {
    private String id;
    private String error;
    private String description;
}