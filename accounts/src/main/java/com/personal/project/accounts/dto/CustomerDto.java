package com.personal.project.accounts.dto;

import com.personal.project.accounts.entity.BaseEntity;
import lombok.*;

@Data
public class CustomerDto extends BaseEntity {

    private String name;

    private String email;

    private String mobileNumber;

}
