package com.personal.project.accounts.service;

import com.personal.project.accounts.dto.CustomerDto;
import com.personal.project.accounts.dto.ResponseDto;

public interface IAccountService {
    /***
     *
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto getAccountByMobileNumber(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
