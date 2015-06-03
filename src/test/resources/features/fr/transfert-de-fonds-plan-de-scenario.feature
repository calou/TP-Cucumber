# language: fr

Fonctionnalité: Transfert de fonds avec plan de scénario
  
  Plan du scénario: Transferts de fonds valides
    Soit les comptes suivants:
      | compte             | solde                     |
      | <compte_debiteur>  | <solde_debiteur_initial>  |
      | <compte_crediteur> | <solde_crediteur_initial> |
    Quand un transfert de fonds de <montant> unités monétaires est effectué depuis le compte bancaire "<compte_debiteur>" vers le compte bancaire "<compte_crediteur>"
    Alors le transfert est <validite>
    Et les soldes des comptes bancaires sont :
      | compte             | solde                   |
      | <compte_debiteur>  | <solde_debiteur_final>  |
      | <compte_crediteur> | <solde_crediteur_final> |

    Exemples:
      | montant | validite | compte_debiteur | solde_debiteur_initial | solde_debiteur_final | compte_crediteur | solde_crediteur_initial | solde_crediteur_final |
      | 50      | valide   | A               | 500                    | 450                  | B                | 100                     | 150                   |
      | 1000    | invalide | A               | 500                    | 500                  | B                | 100                     | 100                   |