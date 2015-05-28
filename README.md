# TP sur Cucumber
##Avant-propos
Ceci est projet permettant d'illustrer l'utilisation de la bibliothèque Cucumber.

Le sujet de l'application sur laquelle porte les spécifications est une simple application bancaire permettant de transférer des fonds d'un compte vers un autre compte.

#Exercice 1

##Etape 1
Le product owner vient vous voir et vous dit:
> J'ai un petit problème: lorsque j'effectue une transaction d'un montant X depuis un compte dont le solde est inférieur à X alors la transaction doit être refusée.

####Objectif
1. Ajouter un scénario permettant de tester le cas de figure décrit par le product owner
2. Rajouter un step permettant vérifier le status du transferts de fonds

##Etape 2
####Objectif
Créer une nouvelle feature pour implémenter le scénario avec tableau

##Etape 3 
####Objectif
Créer une nouvelle feature pour implémenter un plan de scénario

##Exercice 2
Le product owner revient vous voir et vous dit:
> Je viens de discuter avec le client et il m'a dit que les comptes bancaires être à découvert...
> Ah oui, et il faudrait aussi avoir la possibilité de geler un compte...  Donc toute transaction pour laquelle le compte débiteur ou le compte créditeur est gelé sera refusée

####Objectif de l'exercice
Créer une feature permettant de tester les cas ci-dessus


