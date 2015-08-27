# language: fr

Fonctionnalité: Transfert de fonds

  Scénario: Transferts de fonds valides
    Soit les comptes suivants:
      | A | 500 | normal |
      | B | 100 | normal |
    Quand un transfert de fonds de 50 unités monétaires est effectué depuis le compte bancaire "A" vers le compte bancaire "B"
    Alors le transfert est valide
    Et les comptes sont dans l'état suivant:
      | A | 450 |
      | B | 150 |


  Scénario: Transferts de fonds avec découvert
    Soit les comptes suivants:
      | A | 500 | normal |
      | B | 100 | normal |
    Quand un transfert de fonds de 505 unités monétaires est effectué depuis le compte bancaire "A" vers le compte bancaire "B"
    Alors le transfert est valide
    Et les comptes sont dans l'état suivant:
      | A | -5  |
      | B | 605 |


  Scénario: Transferts de fonds invalide pour cause de compte débiteur gelé
    Soit les comptes suivants:
      | A | 500 | gelé |
      | B | 100 | normal |
    Quand un transfert de fonds de 50 unités monétaires est effectué depuis le compte bancaire "A" vers le compte bancaire "B"
    Alors le transfert est invalide pour cause de compte débiteur gelé
    Et les comptes sont dans l'état suivant:
      | A | 500 |
      | B | 100 |

  Scénario: Transferts de fonds invalide pour cause de compte créditeur gelé
    Soit les comptes suivants:
      | A | 500 | normal |
      | B | 100 | gelé |
    Quand un transfert de fonds de 50 unités monétaires est effectué depuis le compte bancaire "A" vers le compte bancaire "B"
    Alors le transfert est invalide pour cause de compte créditeur gelé
    Et les comptes sont dans l'état suivant:
      | A | 500 |
      | B | 100 |