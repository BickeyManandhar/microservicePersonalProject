package com.personal.project.accounts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.personal.project.accounts.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
//when we are mapping entity to dto, some fields are skipped which will show as null if we do not have @JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        name="Customer",
        description = "Schema to hold Customer and Account information."
)
public class CustomerDto extends BaseEntity {

    @NotEmpty(message = "Name cannot be null or empty.")
    @Size(min=5, max=30, message = "The length of the customer must be between 5 and 30.")
    @Schema(
            description = "Name of the customer.",
            example = "Bickey Manandhar"
    )
    private String name;

    @NotEmpty(message = "Email address cannot be empty.")
    @Email(message = "Email address is not valid.")
    @Schema(
            description = "Email of the customer.",
            example = "manandharbickey@gmail.com"
    )
    private String email;

    @NotEmpty(message = "Mobile Number cannot be empty.")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits.")
    @Schema(
            description = "Mobile Number of the customer.",
            example = "7180000000"
    )
    private String mobileNumber;

    @Schema(
            description = "Accounts detail of the customer."
    )
    private AccountsDto accountsDto;

}
