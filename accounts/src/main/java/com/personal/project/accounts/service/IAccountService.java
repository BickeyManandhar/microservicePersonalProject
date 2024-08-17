package com.personal.project.accounts.service;

import com.personal.project.accounts.dto.CustomerDto;

public interface IAccountService {
    /***
     *
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);
}
