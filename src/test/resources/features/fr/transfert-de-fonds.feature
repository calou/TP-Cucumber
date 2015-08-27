# language: fr

Fonctionnalité: Transfert de fonds

  Scénario: Transferts de fonds valides
    Soit un compte bancaire "A" disposant d'un solde de 500 unités monétaires, "normal"
    Et un compte bancaire "B" disposant d'un solde de 100 unités monétaires, "normal"
    Quand un transfert de fonds de 50 unités monétaires est effectué depuis le compte bancaire "A" vers le compte bancaire "B"
    Alors le transfert est valide
    Et le solde du compte bancaire "A" est de 450 unités monétaires
    Et le solde du compte bancaire "B" est de 150 unités monétaires


  Scénario: Transferts de fonds avec découvert
    Soit un compte bancaire "A" disposant d'un solde de 500 unités monétaires, "normal"
    Et un compte bancaire "B" disposant d'un solde de 100 unités monétaires, "normal"
    Quand un transfert de fonds de 505 unités monétaires est effectué depuis le compte bancaire "A" vers le compte bancaire "B"
    Alors le transfert est valide
    Et le solde du compte bancaire "A" est de -5 unités monétaires
    Et le solde du compte bancaire "B" est de 605 unités monétaires

  Scénario: Transferts de fonds invalide pour cause de compte débiteur gelé
    Soit un compte bancaire "A" disposant d'un solde de 500 unités monétaires, "gelé"
    Et un compte bancaire "B" disposant d'un solde de 100 unités monétaires, "normal"
    Quand un transfert de fonds de 50 unités monétaires est effectué depuis le compte bancaire "A" vers le compte bancaire "B"
    Alors le transfert est invalide pour cause de compte débiteur gelé
    Et le solde du compte bancaire "A" est de 500 unités monétaires
    Et le solde du compte bancaire "B" est de 100 unités monétaires

  Scénario: Transferts de fonds invalide pour cause de compte créditeur gelé
    Soit un compte bancaire "A" disposant d'un solde de 500 unités monétaires, "normal"
    Et un compte bancaire "B" disposant d'un solde de 100 unités monétaires, "gelé"
    Quand un transfert de fonds de 50 unités monétaires est effectué depuis le compte bancaire "A" vers le compte bancaire "B"
    Alors le transfert est invalide pour cause de compte créditeur gelé
    Et le solde du compte bancaire "A" est de 500 unités monétaires
    Et le solde du compte bancaire "B" est de 100 unités monétaires