package com.personal.project.accounts.dto;

import lombok.Data;

@Data
public class CustomerAndAccountDto {

    private String name;

    private String email;

    private String mobileNumber;

    private Long accountNumber;

    private String accountType;

    private String branchAddress;
}
