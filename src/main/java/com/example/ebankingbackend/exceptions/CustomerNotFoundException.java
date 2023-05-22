package com.example.ebankingbackend.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String customer_not_found){
        super(customer_not_found); // message passé à l'exception de type RunTimeEx
    }
}
