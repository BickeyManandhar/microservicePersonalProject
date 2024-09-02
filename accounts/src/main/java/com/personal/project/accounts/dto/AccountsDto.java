package com.personal.project.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name="Accounts",
        description = "Schema to hold Account information."
)
public class AccountsDto {

    @NotEmpty(message = "Account Number cannot be empty.")
    @Pattern(regexp = "^\\d{10}$", message = "Account number must be 10 digits.")
    @Schema(
            description = "Account Number of the customer."
    )
    private Long accountNumber;

    @Schema(
            description = "Account type the customer has.",
            example = "Savings"
    )
    @NotEmpty(message = "Account type cannot be empty.")
    private String accountType;

    @Schema(
            description = "Branch address of the bank.",
            example = "1234 some st., someCity, someState 12345, USA"
    )
    @NotEmpty(message = "Branch Address cannot be empty.")
    private String branchAddress;
}
