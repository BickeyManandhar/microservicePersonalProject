package com.personal.project.accounts.controller;

import com.personal.project.accounts.constants.AccountsConstants;
import com.personal.project.accounts.dto.CustomerDto;
import com.personal.project.accounts.dto.ErrorResponseDto;
import com.personal.project.accounts.dto.ResponseDto;
import com.personal.project.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "This is a CRUD REST APIs for Accounts in my personal Bank Project.",
        description = "CRUD REST APIs in my personal Bank Project to CREATE, RETRIEVE, UPDATE and DELETE account details. "
)
@RestController
@RequestMapping(path = "/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor //there is only one constructor created by this, so no @Autowired required
@Validated
public class AccountController {

    private IAccountService accountService;

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer and Account."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error.",
                    //ErrorResposneDto will not be shown in the swagger documentation unless we specify it
                    //it is becuase we are using it only inside GlobalExceptionHandler
                    content= @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto); // if this throws exception it will never go to next line but will go to global exception handler instead
        return ResponseEntity
                .status(HttpStatus.CREATED) //this goes to header
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201)); //this goes to body
    }

    @Operation(
            summary = "Fetch Account REST API",
            description = "REST API to fetch Customer and Account details based on a mobile number."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error.",
                    //ErrorResposneDto will not be shown in the swagger documentation unless we specify it
                    //it is becuase we are using it only inside GlobalExceptionHandler
                    content= @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> getAccountByMobileNumber(@RequestParam
                                                                @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits.")
                                                                String mobileNumber) {
        CustomerDto customerDto = accountService.getAccountByMobileNumber(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @Operation(
            summary = "Update Account REST API",
            description = "REST API to update Customer and Account details based on a account number."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error.",
                    //ErrorResposneDto will not be shown in the swagger documentation unless we specify it
                    //it is becuase we are using it only inside GlobalExceptionHandler
                    content= @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Exception Failed."
            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK) //this goes to header
                    .body(new ResponseDto(AccountsConstants.MESSAGE_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED) //this goes to header
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Account REST API",
            description = "REST API to delete Customer and Account details based on a mobile number."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error.",
                    //ErrorResposneDto will not be shown in the swagger documentation unless we specify it
                    //it is becuase we are using it only inside GlobalExceptionHandler
                    content= @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Exception Failed."
            )
    }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                     @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits.")
                                                     String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK) //this goes to header
                    .body(new ResponseDto(AccountsConstants.MESSAGE_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED) //this goes to header
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }

}
