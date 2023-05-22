package com.example.ebankingbackend.dtos;

import com.example.ebankingbackend.enums.AccountStatus;
import lombok.Data;

import java.util.Date;

@Data
public class CurrentBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status; // enumeration de status
    private CustomerDTO customerdto;
    public double overdraft;
}
