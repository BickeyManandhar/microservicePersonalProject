package com.personal.project.accounts.service.impl;

import com.personal.project.accounts.constants.AccountsConstants;
import com.personal.project.accounts.dto.AccountsDto;
import com.personal.project.accounts.dto.CustomerDto;
import com.personal.project.accounts.entity.Accounts;
import com.personal.project.accounts.entity.Customer;
import com.personal.project.accounts.exception.CustomerAlreadyExistException;
import com.personal.project.accounts.exception.ResourceNotFoundException;
import com.personal.project.accounts.mapper.AccountsMapper;
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
        //customer.setCreatedAt(LocalDateTime.now()); //no need, as we added some annotations and Spring will handle it now
        //customer.setCreatedBy("Anonymous"); //no need, as we added some annotations and Spring will handle it now
        Customer savedCustomer= customerRepository.save(customer);
        accountsRepository.save(createNewAccountEntity(savedCustomer));
    }

    /***
     *
     * @param mobileNumber
     * @return
     */
    @Override
    public CustomerDto getAccountByMobileNumber(String mobileNumber) {
        //old way
//        Optional<Customer> optionalCustomer=customerRepository.findByMobileNumber(mobileNumber);
//        if(optionalCustomer.isEmpty()){
//            throw new ResourceNotFoundException("Customer","mobileNumber",mobileNumber);
//        }else{
//            Optional<Accounts> optionalAccount=accountsRepository.findByCustomerId(optionalCustomer.get().getCustomerId());
//            CustomerDto customerDto = CustomerMapper.convertEntityToDto(optionalCustomer.get(),new CustomerDto());
//            AccountsDto accountDto = AccountsMapper.convertEntityToDto(optionalAccount.get(),new AccountsDto());
//            CustomerAndAccountDto customerAndAccountDto = new CustomerAndAccountDto();
//            customerAndAccountDto.setAccountNumber(accountDto.getAccountNumber());
//            customerAndAccountDto.setAccountType(accountDto.getAccountType());
//            customerAndAccountDto.setBranchAddress(accountDto.getBranchAddress());
//            customerAndAccountDto.setName(customerDto.getName());
//            customerAndAccountDto.setMobileNumber(mobileNumber);
//            customerAndAccountDto.setEmail(customerDto.getEmail());
//            return customerAndAccountDto;
//        }
        //new way
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );
        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account","customerId",customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.convertEntityToDto(customer, new CustomerDto());
        AccountsDto accountsDto = AccountsMapper.convertEntityToDto(account, new AccountsDto());
        customerDto.setAccountsDto(accountsDto);
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        //since accoountnumber is the PK for Accounts table we are not allowing client to change account number by doing the following check
        //also we are using it as PK to get the Customer whose detail needs to be changes
        if(accountsDto!=null){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    ()-> new ResourceNotFoundException("Account","AccountNumber",accountsDto.getAccountType().toString())
            );
            AccountsMapper.convertDtoToEntity(accountsDto, accounts);
            accounts=accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    ()-> new ResourceNotFoundException("Customer","customerId",customerId.toString())
            );
            CustomerMapper.convertDtoToEntity(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        boolean isDeleted = false;
        if(mobileNumber!=null){
            Customer customerEntity = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                    ()-> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
            );
            Long customerId = customerEntity.getCustomerId();
            customerRepository.deleteById(customerId);
            accountsRepository.deleteByCustomerId(customerId);
            isDeleted = true;
        }
        return isDeleted;
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
        //newAccount.setCreatedAt(LocalDateTime.now()); //no need, as we added some annotations and Spring will handle it now
        //newAccount.setCreatedBy("Anonymous");//no need, as we added some annotations and Spring will handle it now
        return newAccount;
    }
}
