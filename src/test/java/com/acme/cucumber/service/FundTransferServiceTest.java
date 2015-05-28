package com.acme.cucumber.service;

import com.acme.cucumber.model.BankAccount;
import com.acme.cucumber.model.FundTransfer;
import com.acme.cucumber.model.FundTransferResult;
import com.acme.cucumber.model.FundTransferStatus;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


public class FundTransferServiceTest {

    private FundTransferService fundTransferService;

    @Before
    public void setUp() throws Exception {
        fundTransferService = new FundTransferService();
    }

    @Test
    public void applyNominalCase() {
        BankAccount debtor = BankAccount.builder().balance(new BigDecimal(1000)).build();
        BankAccount creditor = BankAccount.builder().balance(new BigDecimal(1000)).build();
        FundTransfer fundTransfer = FundTransfer.builder().debtorBankAccount(debtor).creditorBankAccount(creditor).amount(new BigDecimal(500)).build();

        FundTransferResult fundTransferResult = fundTransferService.apply(fundTransfer);
        assertThat(fundTransferResult.getFundTransfer()).isEqualTo(fundTransfer);
        assertThat(fundTransferResult.getStatus()).isEqualTo(FundTransferStatus.OK);
        assertThat(debtor.getBalance()).isEqualTo(new BigDecimal(500));
        assertThat(creditor.getBalance()).isEqualTo(new BigDecimal(1500));
    }

    @Test
    public void applyWithInsufficientBalance() {
        BankAccount debtor = BankAccount.builder().balance(new BigDecimal(200)).build();
        BankAccount creditor = BankAccount.builder().balance(new BigDecimal(1000)).build();
        FundTransfer fundTransfer = FundTransfer.builder().debtorBankAccount(debtor).creditorBankAccount(creditor).amount(new BigDecimal(500)).build();

        FundTransferResult fundTransferResult = fundTransferService.apply(fundTransfer);
        assertThat(fundTransferResult.getFundTransfer()).isEqualTo(fundTransfer);
        assertThat(fundTransferResult.getStatus()).isEqualTo(FundTransferStatus.DEBTOR_INSUFFICIENT_BALANCE);
        assertThat(debtor.getBalance()).isEqualTo(new BigDecimal(200));
        assertThat(creditor.getBalance()).isEqualTo(new BigDecimal(1000));
    }

    @Test
    public void applyWithFrozenDebtor() {
        BankAccount debtor = BankAccount.builder().balance(new BigDecimal(1000)).frozen(true).build();
        BankAccount creditor = BankAccount.builder().balance(new BigDecimal(1000)).build();
        FundTransfer fundTransfer = FundTransfer.builder().debtorBankAccount(debtor).creditorBankAccount(creditor).amount(new BigDecimal(500)).build();

        FundTransferResult fundTransferResult = fundTransferService.apply(fundTransfer);
        assertThat(fundTransferResult.getFundTransfer()).isEqualTo(fundTransfer);
        assertThat(fundTransferResult.getStatus()).isEqualTo(FundTransferStatus.FROZEN_DEBTOR);
        assertThat(debtor.getBalance()).isEqualTo(new BigDecimal(1000));
        assertThat(creditor.getBalance()).isEqualTo(new BigDecimal(1000));
    }

    @Test
    public void applyWithFrozenCreditor() {
        BankAccount debtor = BankAccount.builder().balance(new BigDecimal(1000)).build();
        BankAccount creditor = BankAccount.builder().balance(new BigDecimal(1000)).frozen(true).build();
        FundTransfer fundTransfer = FundTransfer.builder().debtorBankAccount(debtor).creditorBankAccount(creditor).amount(new BigDecimal(500)).build();

        FundTransferResult fundTransferResult = fundTransferService.apply(fundTransfer);
        assertThat(fundTransferResult.getFundTransfer()).isEqualTo(fundTransfer);
        assertThat(fundTransferResult.getStatus()).isEqualTo(FundTransferStatus.FROZEN_CREDITOR);
        assertThat(debtor.getBalance()).isEqualTo(new BigDecimal(1000));
        assertThat(creditor.getBalance()).isEqualTo(new BigDecimal(1000));
    }
}