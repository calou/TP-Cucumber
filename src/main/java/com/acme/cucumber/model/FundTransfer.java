package com.acme.cucumber.model;

import lombok.*;

import java.math.BigDecimal;

/**
 * Classe représentant un transfert de fond d'un compte débiteur vers un compte créditeur
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundTransfer {
    /**
     * Le compte bancaire débiteur
     */
    private BankAccount debtorBankAccount;

    /**
     * Le compte bancaire créditeur
     */
    private BankAccount creditorBankAccount;

    /**
     * Montant de la transaction
     */
    private BigDecimal amount;
}
