package com.personal.project.accounts.service.impl;

import com.personal.project.accounts.constants.AccountsConstants;
import com.personal.project.accounts.dto.AccountsDto;
import com.personal.project.accounts.dto.CustomerAndAccountDto;
import com.personal.project.accounts.dto.CustomerDto;
import com.personal.project.accounts.entity.Accounts;
import com.personal.project.accounts.entity.Customer;
import com.personal.project.accounts.exception.CustomerAlreadyExistException;
import com.personal.project.accounts.exception.CustomerDoesNotExistException;
import com.personal.project.accounts.mapper.AccountsMapper;
import com.personal.project.accounts.mapper.CustomerMapper;
import com.personal.project.accounts.repository.AccountsRepository;
import com.personal.project.accounts.repository.CustomerRepository;
import com.personal.project.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor //there is only one constructor created by this, so no @Autowired required
public class AccountServiceImpl implements IAccountService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /***
     *
     * @param customerDto
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.convertDtoToEntity(customerDto, new Customer());
        Optional<Customer> optionalCustomer=customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistException("Customer already registered with given mobile number : "+customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer= customerRepository.save(customer);
        accountsRepository.save(createNewAccountEntity(savedCustomer));
    }

    /***
     *
     * @param mobileNumber
     * @return
     */
    @Override
    public CustomerAndAccountDto getAccountByMobileNumber(String mobileNumber) {
        Optional<Customer> optionalCustomer=customerRepository.findByMobileNumber(mobileNumber);
        if(optionalCustomer.isEmpty()){
            throw new CustomerDoesNotExistException("No Customer is registered with this mobile number : "+mobileNumber);
        }else{
            Optional<Accounts> optionalAccount=accountsRepository.findByCustomerId(optionalCustomer.get().getCustomerId());
            CustomerDto customerDto = CustomerMapper.convertEntityToDto(optionalCustomer.get(),new CustomerDto());
            AccountsDto accountDto = AccountsMapper.convertEntityToDto(optionalAccount.get(),new AccountsDto());
            CustomerAndAccountDto customerAndAccountDto = new CustomerAndAccountDto();
            customerAndAccountDto.setAccountNumber(accountDto.getAccountNumber());
            customerAndAccountDto.setAccountType(accountDto.getAccountType());
            customerAndAccountDto.setBranchAddress(accountDto.getBranchAddress());
            customerAndAccountDto.setName(customerDto.getName());
            customerAndAccountDto.setMobileNumber(mobileNumber);
            customerAndAccountDto.setEmail(customerDto.getEmail());
            return customerAndAccountDto;
        }
    }

    /***
     *
     * @param customer
     * @return
     */
    private Accounts createNewAccountEntity(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccountNumber = 1000000000L + new Random().nextInt(900000000); //we are generating random account number here

        newAccount.setAccountNumber(randomAccountNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }
}
