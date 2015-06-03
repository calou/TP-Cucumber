# language: fr

Fonctionnalité: Transfert de fonds
  Contexte:
    Soit un compte bancaire "A" disposant d'un solde de 500 unités monétaires
    Et un compte bancaire "B" disposant d'un solde de 100 unités monétaires

  Scénario: Transferts de fonds valides
    Quand un transfert de fonds de 50 unités monétaires est effectué depuis le compte bancaire "A" vers le compte bancaire "B"
    Alors le transfert est valide
    Et le solde du compte bancaire "A" est de 450 unités monétaires
    Et le solde du compte bancaire "B" est de 150 unités monétaires

  Scénario: Transferts de fonds invalides
    Quand un transfert de fonds de 1000 unités monétaires est effectué depuis le compte bancaire "A" vers le compte bancaire "B"
    Alors le transfert est invalide
    Et le solde du compte bancaire "A" est de 500 unités monétaires
    Et le solde du compte bancaire "B" est de 100 unités monétaires