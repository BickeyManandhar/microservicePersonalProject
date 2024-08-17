package com.personal.project.accounts.mapper;

import com.personal.project.accounts.dto.CustomerDto;
import com.personal.project.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDto convertEntityToDto(Customer customerEntity, CustomerDto customerDto) {
        customerDto.setEmail(customerEntity.getEmail());
        customerDto.setName(customerEntity.getName());
        customerDto.setMobileNumber(customerEntity.getMobileNumber());
        return customerDto;
    }

    public static Customer convertDtoToEntity(CustomerDto customerDto, Customer customerEntity) {
        customerEntity.setEmail(customerDto.getEmail());
        customerEntity.setName(customerDto.getName());
        customerEntity.setMobileNumber(customerDto.getMobileNumber());
        return customerEntity;
    }
}
