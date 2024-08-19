package com.personal.project.accounts.controller;

import com.personal.project.accounts.constants.AccountsConstants;
import com.personal.project.accounts.dto.CustomerAndAccountDto;
import com.personal.project.accounts.dto.CustomerDto;
import com.personal.project.accounts.dto.ResponseDto;
import com.personal.project.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor //there is only one constructor created by this, so no @Autowired required
public class AccountController {

    private IAccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){
        accountService.createAccount(customerDto); // if this throws exception it will never go to next line but will go to global exception handler instead
        return ResponseEntity
                .status(HttpStatus.CREATED) //this goes to header
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201)); //this goes to body
    }

    @GetMapping("/getAccountAndCustomer")
    public ResponseEntity<CustomerAndAccountDto> getAccountByMobileNumber(@RequestParam String mobileNumber){
        CustomerAndAccountDto customerAndAccountDto=accountService.getAccountByMobileNumber(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerAndAccountDto);
    }

}
