package com.personal.project.accounts.controller;

import com.personal.project.accounts.constants.AccountsConstants;
import com.personal.project.accounts.dto.CustomerDto;
import com.personal.project.accounts.dto.ResponseDto;
import com.personal.project.accounts.service.IAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor //there is only one constructor created by this, so no @Autowired required
@Validated
public class AccountController {

    private IAccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        accountService.createAccount(customerDto); // if this throws exception it will never go to next line but will go to global exception handler instead
        return ResponseEntity
                .status(HttpStatus.CREATED) //this goes to header
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201)); //this goes to body
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> getAccountByMobileNumber(@RequestParam
                                                                    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits.")
                                                                    String mobileNumber){
        CustomerDto customerDto=accountService.getAccountByMobileNumber(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdated= accountService.updateAccount(customerDto);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK) //this goes to header
                    .body(new ResponseDto(AccountsConstants.MESSAGE_200,AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR) //this goes to header
                    .body(new ResponseDto(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                         @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits.")
                                                         String mobileNumber){
        boolean isDeleted= accountService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK) //this goes to header
                    .body(new ResponseDto(AccountsConstants.MESSAGE_200,AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR) //this goes to header
                    .body(new ResponseDto(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
        }
    }

}
