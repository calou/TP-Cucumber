package com.acme.cucumber.model;

import lombok.*;

/**
 * Classe représentant le résultat d'un transfert de fonds
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundTransferResult {
    private FundTransfer fundTransfer;
    private FundTransferStatus status;
}
