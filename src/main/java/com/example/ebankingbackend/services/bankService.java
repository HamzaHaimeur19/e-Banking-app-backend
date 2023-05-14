package com.example.ebankingbackend.services;

import com.example.ebankingbackend.entities.BankAccount;
import com.example.ebankingbackend.entities.CurrentAccount;
import com.example.ebankingbackend.entities.SavingAccount;
import com.example.ebankingbackend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional // on utilise transactional car consulter est une methode transactionnelle
public class bankService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public void consulter() {
        BankAccount bankAccount = bankAccountRepository.findById("009c8046-36ad-4300-b241-ee95c455a9c0").orElse(null);
        if (bankAccount != null) {
            System.out.println("-------infos banque:--------");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getCustomer().getNom());
            System.out.println(bankAccount.getClass().getSimpleName()); // avoir le nom de la classe fille
            if (bankAccount instanceof CurrentAccount) {
                System.out.println("overdraft =>" + ((CurrentAccount) bankAccount).getOverDraft());
            } else if (bankAccount instanceof SavingAccount) {
                System.out.println("interest rate => " + ((SavingAccount) bankAccount).getInterestRate());
            }

            // fonctionne en mode lazy charger les operations car on les a demandé
            bankAccount.getAccountOperations().forEach(op -> {
                System.out.println("----operations:------");
                System.out.println(op.getType());
                System.out.println(op.getOperationDate());
                System.out.println(op.getAmount());
            });
        }
    }
}
