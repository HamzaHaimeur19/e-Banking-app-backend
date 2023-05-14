package com.example.ebankingbackend.entities;

import com.example.ebankingbackend.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // type d'heritage entre classe mere et classes filles
@DiscriminatorColumn(name="TYPE", length=4) // valeur donnée au types de classes filles (types de comptes)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    @Id
    private String id;
    private double balance;
    private Date createdAt;
    @Enumerated(EnumType.STRING) // stocker enumeration en bd sous format String
    private AccountStatus status; // enumeration de status
    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "bankAccount", fetch = FetchType.LAZY)
    private List<AccountOperation> accountOperations;



}
