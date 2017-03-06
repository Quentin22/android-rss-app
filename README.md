# android-rss-app

## Objectif de l'application
Développer une application Android (simple) qui permet d’afficher les news à la une en utilisant le flux RSS suivant http://www.lemonde.fr.

L'application, doit afficher l’ensemble des news à la une avec au minimum le titre de la news et l’image associée à la news. En cliquant sur une news il doit être possible de voir le détail de cette news (le titre, l’image, la description et un lien vers la news complète).

## L'implémentation

![alt text](https://github.com/Quentin22/android-rss-app/blob/master/demo.gif)

### Choix
Pour le développement de cette application, j'ai choisi de cibler la version Android 7.1 (Nougat) avec une compatibilité minimum jusqu'à la version 6.0 (Marshmallow). Pour un développement en production j'aurais plutôt choisi la version 5.1 (Lollipop) avec une compatibilité minimum jusqu'à la version 4.4 (KitKat) dans le but de cibler le plus d'utilisateur possible. 

### Problèmes rencontrés

La difficulté que j'ai rencontré pour le développement de cette application était pour parser le flux RSS, j'y ai passé un peu de temps.


### Améliorations

- Concernant les améliorations il serait intéressant de créer un menu latéral comportant les sections associés aux actualités :
`http://www.lemonde.fr/`**`election-presidentielle-2017`**`/article...
http://www.lemonde.fr/`**`international`**`/article...
http://www.lemonde.fr/`**`police-justice`**`/article...
http://www.lemonde.fr/`**`culture`**`/article...`<br />
Lors de la récupération des actualités il serait possible de modifier les items du menu avec les nouvelles sections associées aux nouvelles actualités.

- La date de publication est disponible dans chaque actualité et donc l'afficher dans la vue de l'actualité en détail
`pubDate>Sun, 05 Mar 2017 19:33:44 +0100</pubDate>`. Il faudrait parser la date pour la rendre plus lisible sur l'application. Soit en mettant le jour et l'heure de publication ou bien en mettant le temps écoulé depuis la publication (Principe des posts Facebook).

- Créer un service qui de manière périodique mettrait à jour les actualités.

- La création d'un widget qui afficherait la dernière actualité du moment.


#### Temps de développemnt : 6h
- Création du repository sur Github
- Création d'un logo pour l'application
- Développement du parser XML pour le flux RSS
- Création d'une activité avec une listview pour afficher les actualités
- Création d'une seconde activité pour les détails d'une actualité à la fois
- Ecriture de la documentation
