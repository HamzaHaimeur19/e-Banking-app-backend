package com.example.ebankingbackend;

import com.example.ebankingbackend.dtos.BankAccountDTO;
import com.example.ebankingbackend.dtos.CurrentBankAccountDTO;
import com.example.ebankingbackend.dtos.CustomerDTO;
import com.example.ebankingbackend.dtos.SavingBankAccountDTO;
import com.example.ebankingbackend.entities.*;
import com.example.ebankingbackend.enums.AccountStatus;
import com.example.ebankingbackend.enums.OperationType;
import com.example.ebankingbackend.exceptions.BalanceNotSufficientException;
import com.example.ebankingbackend.exceptions.BankAccountNotFoundException;
import com.example.ebankingbackend.exceptions.CustomerNotFoundException;
import com.example.ebankingbackend.repositories.AccountOperationRepository;
import com.example.ebankingbackend.repositories.BankAccountRepository;
import com.example.ebankingbackend.repositories.CustomerRepository;
import com.example.ebankingbackend.services.BankAccountImp;
import com.example.ebankingbackend.services.BankAccountService;
import com.example.ebankingbackend.services.bankService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
@AllArgsConstructor
public class EbankingBackendApplication implements CommandLineRunner {
    @Autowired
    private bankService BankService;
    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;
    private BankAccountService bankAccountService;


    public static void main(String[] args) {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Stream.of("Hamza", "Yassine", "Qaddour").forEach(name -> {
//            Customer customer = new Customer();
//            customer.setNom(name);
//            customer.setEmail(name + "@gmail.com");
//            customerRepository.save(customer);
//        });
//
//        customerRepository.findAll().forEach(customer -> {
//            CurrentAccount currentAccount = new CurrentAccount();
//            currentAccount.setId(UUID.randomUUID().toString());
//            currentAccount.setBalance(Math.random() * 900000);
//            currentAccount.setCreatedAt(new Date());
//            currentAccount.setStatus(AccountStatus.CREATED);
//            currentAccount.setCustomer(customer);
//            currentAccount.setOverDraft(9000);
//            bankAccountRepository.save(currentAccount);
//
//            SavingAccount savingAccount = new SavingAccount();
//            savingAccount.setId(UUID.randomUUID().toString());
//            savingAccount.setBalance(Math.random() * 900000);
//            savingAccount.setCreatedAt(new Date());
//            savingAccount.setStatus(AccountStatus.CREATED);
//            savingAccount.setCustomer(customer);
//            savingAccount.setInterestRate(8);
//            bankAccountRepository.save(savingAccount);
//        });


        Stream.of("Hamza", "Hatim", "Rayane").forEach(name -> {
            CustomerDTO c = new CustomerDTO();
            c.setNom(name);
            c.setEmail(name + "@gmail.com");
            bankAccountService.saveCustomer(c);
        });

        //ajouter des comptes courants et epargnes pour les clients de la banque
        bankAccountService.listCustomers().forEach(customer -> {
            try {
                bankAccountService.saveCurrentBankAccout(Math.random() * 90000, Math.random() * 9000, customer.getId());
                bankAccountService.saveSavingBankAccout(Math.random() * 120000, 5.5, customer.getId());

            } catch (CustomerNotFoundException e) {
                e.printStackTrace();
            }  List<BankAccountDTO> bankAccounts = bankAccountService.banklist();
            for (BankAccountDTO bankAccount : bankAccounts) {
                for (int i = 10; i < 10; i++) {
                    String accountId;
                    if(bankAccount instanceof SavingBankAccountDTO){
                        accountId = ((SavingBankAccountDTO) bankAccount).getId();
                    } else {
                        accountId = ((CurrentBankAccountDTO) bankAccount).getId();
                    }
                    bankAccountService.credit(accountId, 10000 * Math.random() + 120000, "CREDIT");
                    bankAccountService.debit(accountId, 1000 * Math.random() * 9000, "Debit");
                }
            }

            //créer des opérations
            bankAccountRepository.findAll().forEach(account -> {
                for (int i = 0; i < 10; i++) {
                    AccountOperation accountOperation = new AccountOperation();

                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random() * 12000);
                    accountOperation.setType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
                    accountOperation.setBankAccount(account);
                    accountOperationRepository.save(accountOperation);
                }
            });
        });
        //methode consulter du service bankService
        BankService.consulter();

    }


}
