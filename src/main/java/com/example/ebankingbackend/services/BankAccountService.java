package com.example.ebankingbackend.services;

import com.example.ebankingbackend.dtos.*;
import com.example.ebankingbackend.entities.BankAccount;
import com.example.ebankingbackend.entities.CurrentAccount;
import com.example.ebankingbackend.entities.Customer;
import com.example.ebankingbackend.entities.SavingAccount;
import com.example.ebankingbackend.exceptions.BankAccountNotFoundException;
import com.example.ebankingbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customer); // ajouter un client

    CurrentBankAccountDTO saveCurrentBankAccout(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException; // pour compte courant

    SavingBankAccountDTO saveSavingBankAccout(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException; // pour compte epargne

    List<CustomerDTO> listCustomers();

    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;

    void debit(String accountId, double amount, String description); // decaisser la monnaie de la banque

    void credit(String accountId, double amount, String description); // encaisser la monnaie à la banque

    void transferer(String accountIdSource, String accountIdDestination, double amount);

    List<BankAccountDTO> banklist();

    CustomerDTO getCustomer(Long customerId);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    List<AccountOperationDTO> accountHistory(String accountId);

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size);
}
