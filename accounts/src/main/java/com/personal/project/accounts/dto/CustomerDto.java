package com.personal.project.accounts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.personal.project.accounts.entity.BaseEntity;
import lombok.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
//when we are mapping entity to dto, some fileds are skipped which will show as null if we do not have @JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto extends BaseEntity {

    private String name;

    private String email;

    private String mobileNumber;

    private AccountsDto accountsDto;

}
