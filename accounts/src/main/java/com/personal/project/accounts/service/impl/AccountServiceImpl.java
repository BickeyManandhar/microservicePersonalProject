package com.personal.project.accounts.service.impl;

import com.personal.project.accounts.constants.AccountsConstants;
import com.personal.project.accounts.dto.CustomerDto;
import com.personal.project.accounts.entity.Accounts;
import com.personal.project.accounts.entity.Customer;
import com.personal.project.accounts.exception.CustomerAlreadyExistException;
import com.personal.project.accounts.mapper.CustomerMapper;
import com.personal.project.accounts.repository.AccountsRepository;
import com.personal.project.accounts.repository.CustomerRepository;
import com.personal.project.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        Customer savedCustomer= customerRepository.save(customer);
        accountsRepository.save(createNewAccountEntity(savedCustomer));
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
        return newAccount;
    }
}
