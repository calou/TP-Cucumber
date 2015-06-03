# language: fr

Fonctionnalité: Transfert de fonds avec tableaux

  Contexte:
    Soit les comptes suivants:
      | compte | solde |
      | A      | 500   |
      | B      | 100   |

  Scénario: Transferts de fonds valides
    Quand un transfert de fonds de 50 unités monétaires est effectué depuis le compte bancaire "A" vers le compte bancaire "B"
    Alors le transfert est valide
    Et les soldes des comptes bancaires sont :
      | compte | solde |
      | A      | 450   |
      | B      | 150   |