package com.modernbank.parameter_service.api.response;


import com.modernbank.parameter_service.api.dto.ErrorCodesDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetAllErrorCodesResponse {
    private List<ErrorCodesDTO> errorCodes;
}