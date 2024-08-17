package com.personal.project.accounts.mapper;

import com.personal.project.accounts.dto.AccountsDto;
import com.personal.project.accounts.entity.Accounts;

public class AccountsMapper {

    public static AccountsDto convertEntityToDto(Accounts accountsEntity, AccountsDto accountsDto){
        accountsDto.setAccountNumber(accountsEntity.getAccountNumber());
        accountsDto.setAccountType(accountsEntity.getAccountType());
        accountsDto.setAccountType(accountsEntity.getAccountType());
        return accountsDto;
    }

    public static Accounts convertDtoToEntity(AccountsDto accountsDto, Accounts accountsEntity){
        accountsEntity.setAccountNumber(accountsDto.getAccountNumber());
        accountsEntity.setAccountType(accountsDto.getAccountType());
        accountsEntity.setAccountType(accountsDto.getAccountType());
        return accountsEntity;
    }
}
