package com.personal.project.accounts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.personal.project.accounts.entity.BaseEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
//when we are mapping entity to dto, some fields are skipped which will show as null if we do not have @JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto extends BaseEntity {

    @NotEmpty(message = "Name cannot be null or empty.")
    @Size(min=5, max=30, message = "The length of the customer must be between 5 and 30.")
    private String name;

    @NotEmpty(message = "Email address cannot be empty.")
    @Email(message = "Email address is not valid.")
    private String email;

    @NotEmpty(message = "Mobile Number cannot be empty.")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits.")
    private String mobileNumber;

    private AccountsDto accountsDto;

}
