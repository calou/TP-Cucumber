package com.acme.cucumber.service;

import com.acme.cucumber.model.BankAccount;
import com.acme.cucumber.model.FundTransfer;
import com.acme.cucumber.model.FundTransferResult;
import com.acme.cucumber.model.FundTransferStatus;

import java.math.BigDecimal;

/**
 * Service gérant les transferts de fonds
 */
public class FundTransferService {
    public FundTransferResult apply(FundTransfer fundTransfer){

        BankAccount debtorBankAccount = fundTransfer.getDebtorBankAccount();
        BankAccount creditorBankAccount = fundTransfer.getCreditorBankAccount();

        FundTransferResult fundTransferResult = FundTransferResult.builder().fundTransfer(fundTransfer).build();

        /*
         * Si le compte débiteur est gelé, alors la transaction n'est pas valide
         */
        if(debtorBankAccount.isFrozen()){
            fundTransferResult.setStatus(FundTransferStatus.FROZEN_DEBTOR);
        }
        /*
         * Si le compte créditeur est gelé, alors la transaction n'est pas valide
         */
        else if (creditorBankAccount.isFrozen()){
            fundTransferResult.setStatus(FundTransferStatus.FROZEN_CREDITOR);
        }
        else {
            BigDecimal debtorNewBalance = debtorBankAccount.getBalance().subtract(fundTransfer.getAmount());
            BigDecimal creditorNewBalance = creditorBankAccount.getBalance().add(fundTransfer.getAmount());
            debtorBankAccount.setBalance(debtorNewBalance);
            creditorBankAccount.setBalance(creditorNewBalance);
            fundTransferResult.setStatus(FundTransferStatus.OK);
        }
        return fundTransferResult;
    }
}
