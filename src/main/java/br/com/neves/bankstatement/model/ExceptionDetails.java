package br.com.neves.bankstatement.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionDetails {
// ------------------------------ FIELDS ------------------------------

    private String title;
    private int status;
    private String details;
    private String developerMessage;
    private LocalDateTime timestamp;
    private String path;
}
