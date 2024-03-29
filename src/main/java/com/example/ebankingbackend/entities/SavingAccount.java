package com.example.ebankingbackend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("SA") //discriminator valeur de type de compte "SA"
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavingAccount extends BankAccount{
    private double interestRate;
}
