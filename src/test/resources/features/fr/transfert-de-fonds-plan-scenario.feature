# language: fr

Fonctionnalité: Transfert de fonds

  Plan du scénario: Transferts de fonds
    Soit les comptes suivants:
      | <compte_debiteur>  | <solde_debiteur_initial>  | <etat_compte_debiteur>  |
      | <compte_crediteur> | <solde_crediteur_initial> | <etat_compte_crediteur> |
    Quand un transfert de fonds de <montant> unités monétaires est effectué depuis le compte bancaire "<compte_debiteur>" vers le compte bancaire "<compte_crediteur>"
    Alors le transfert est <validite>
    Et les comptes sont dans l'état suivant:
      | <compte_debiteur>  | <solde_debiteur_final>  |
      | <compte_crediteur> | <solde_crediteur_final> |


    Exemples: Transferts de fonds valides et invalides
      | compte_debiteur | etat_compte_debiteur | solde_debiteur_initial | compte_crediteur | etat_compte_crediteur | solde_crediteur_initial | montant | validite                                     | solde_debiteur_final | solde_crediteur_final |
      | A               | normal               | 500                    | B                | normal                | 100                     | 50      | valide                                       | 450                  | 150                   |
      | A               | normal               | 500                    | B                | normal                | 100                     | 505     | valide                                       | -5                   | 605                   |
      | A               | gelé                 | 500                    | B                | normal                | 100                     | 50      | invalide pour cause de compte débiteur gelé  | 500                  | 100                   |
      | A               | normal               | 500                    | B                | gelé                  | 100                     | 50      | invalide pour cause de compte créditeur gelé | 500                  | 100                   |