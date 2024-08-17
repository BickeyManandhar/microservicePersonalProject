package com.personal.project.accounts.controller;

import com.personal.project.accounts.constants.AccountsConstants;
import com.personal.project.accounts.dto.CustomerDto;
import com.personal.project.accounts.dto.ResponseDto;
import com.personal.project.accounts.service.impl.AccountServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor //there is only one constructor created by this, so no @Autowired required
public class AccountController {

    private AccountServiceImpl accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){

        return ResponseEntity
                .status(HttpStatus.CREATED) //this goes to header
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201)); //this goes to body
    }

}
