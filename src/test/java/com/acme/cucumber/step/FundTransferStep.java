package com.acme.cucumber.step;

import com.acme.cucumber.model.BankAccount;
import com.acme.cucumber.model.FundTransfer;
import com.acme.cucumber.model.FundTransferResult;
import com.acme.cucumber.model.FundTransferStatus;
import com.acme.cucumber.service.FundTransferService;
import cucumber.api.java.Before;
import cucumber.api.java.fr.Alors;
import cucumber.api.java.fr.Quand;
import cucumber.api.java.fr.Soit;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class FundTransferStep {

    Map<String, Object> storageMap;
    FundTransferService fundTransferService;

    @Before
    public void setUp(){
        storageMap = new HashMap<>();
        fundTransferService = new FundTransferService();
    }

    @Soit("^un compte bancaire \"(.*?)\" disposant d'un solde de (\\d+) unités monétaires, \"(.*?)\"$")
    public void un_compte_bancaire_disposant_d_un_solde_de_unités_monétaires(String accountName, int balance, String status) throws Throwable {
        BankAccount bankAccount = BankAccount.builder().balance(new BigDecimal(balance)).frozen("gelé".equals(status)).build();
        storageMap.put(accountName, bankAccount);
    }

    @Quand("^un transfert de fonds de (\\d+) unités monétaires est effectué depuis le compte bancaire \"(.*?)\" vers le compte bancaire \"(.*?)\"$")
    public void un_transfert_de_fonds_de_unités_monétaires_est_effectué_depuis_le_compte_bancaire_vers_le_compte_bancaire(int amount, String debtorAccountName, String creditorAccountName) throws Throwable {
        BankAccount debtor = (BankAccount)storageMap.get(debtorAccountName);
        BankAccount creditor = (BankAccount)storageMap.get(creditorAccountName);
        FundTransfer fundTransfer = FundTransfer.builder().amount(new BigDecimal(amount)).debtorBankAccount(debtor).creditorBankAccount(creditor).build();
        FundTransferResult fundTransferResult = fundTransferService.apply(fundTransfer);
        storageMap.put("fund_transfer_result", fundTransferResult);
    }

    @Alors("^le transfert est valide$")
    public void le_transfert_est_valide() throws Throwable {
        FundTransferResult fundTransferResult = (FundTransferResult)storageMap.get("fund_transfer_result");
        assertThat(fundTransferResult.getStatus()).isEqualTo(FundTransferStatus.OK);
    }

    @Alors("^le transfert est invalide pour cause de fond insuffisant$")
    public void le_transfert_est_invalide_pour_cause_de_fond_insuffisant() throws Throwable {
        FundTransferResult fundTransferResult = (FundTransferResult)storageMap.get("fund_transfer_result");
        assertThat(fundTransferResult.getStatus()).isEqualTo(FundTransferStatus.DEBTOR_INSUFFICIENT_BALANCE);
    }

    @Alors("^le transfert est invalide pour cause de compte créditeur gelé$")
    public void le_transfert_est_invalide_pour_cause_de_compte_crediteur_gele() throws Throwable {
        FundTransferResult fundTransferResult = (FundTransferResult)storageMap.get("fund_transfer_result");
        assertThat(fundTransferResult.getStatus()).isEqualTo(FundTransferStatus.FROZEN_CREDITOR);
    }

    @Alors("^le transfert est invalide pour cause de compte débiteur gelé$")
    public void le_transfert_est_invalide_pour_cause_de_compte_debiteur_gele() throws Throwable {
        FundTransferResult fundTransferResult = (FundTransferResult)storageMap.get("fund_transfer_result");
        assertThat(fundTransferResult.getStatus()).isEqualTo(FundTransferStatus.FROZEN_DEBTOR);
    }



    @Alors("^le solde du compte bancaire \"(.*?)\" est de (-?\\d+) unités monétaires$")
    public void le_solde_du_compte_bancaire_est_de_unités_monétaires(String accountName, int balance) throws Throwable {
        BankAccount bankAccount = (BankAccount)storageMap.get(accountName);
        assertThat(bankAccount.getBalance()).isEqualTo(new BigDecimal(balance));
    }


    @Soit("^les comptes suivants:$")
    public void les_comptes_suivants(List<List<String>> accounts) throws Throwable {
        for (List<String> account : accounts) {
            un_compte_bancaire_disposant_d_un_solde_de_unités_monétaires(account.get(0), Integer.parseInt(account.get(1)), account.get(2));
        }
    }

    @Alors("^les comptes sont dans l'état suivant:$")
    public void les_comptes_sont_dans_l_état_suivant(List<List<String>> accounts) throws Throwable {
        for (List<String> account : accounts) {
            le_solde_du_compte_bancaire_est_de_unités_monétaires(account.get(0), Integer.parseInt(account.get(1)));
        }
    }


}
