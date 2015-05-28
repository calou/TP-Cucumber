package com.acme.cucumber.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    private BigDecimal balance;
    private boolean frozen;
}
