package com.personal.project.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty(message = "Account Number cannot be empty.")
    @Pattern(regexp = "^\\d{10}$", message = "Account number must be 10 digits.")
    private Long accountNumber;

    @NotEmpty(message = "Account type cannot be empty.")
    private String accountType;

    @NotEmpty(message = "Branch Address cannot be empty.")
    private String branchAddress;
}
