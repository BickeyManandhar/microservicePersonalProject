package com.personal.project.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(
        name="Loans",
        description = "Schema to hold Loan information."
)
public class LoansDto {

    @NotEmpty(message = "Mobile Number cannot be empty.")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits.")
    @Schema(
            description = "Mobile Number of the customer.",
            example = "7180000000"
    )
    private String mobileNumber;

    @NotEmpty(message = "Loan Number cannot be empty.")
    @Pattern(regexp = "^\\d{12}$", message = "Loan number must be 12 digits.")
    @Schema(
            description = "Loan Number of the customer.",
            example = "123456789011"
    )
    private String loanNumber;

    @NotEmpty(message = "Loan type cannot be empty.")
    @Schema(
            description = "Type of the loan", example = "Auto Loan"
    )
    private String loanType;

    @Positive(message = "Total loan amount should be greater than zero")
    @Schema(
            description = "Total loan amount", example = "100000"
    )
    private int totalLoan;

    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    @Schema(
            description = "Total loan amount paid", example = "1000"
    )
    private int amountPaid;

    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    @Schema(
            description = "Total outstanding amount against a loan", example = "99000"
    )
    private int outstandingAmount;
}
