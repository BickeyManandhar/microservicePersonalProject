package com.personal.project.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErroResponseDto {

    private String apiPath;
    private HttpStatus errorCode;
    private String errorMessage;
    private LocalDateTime timestamp;

}
