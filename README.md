# android-rss-app

## Objectif de l'application
Développer une application Android (simple) qui permet d’afficher les news à la une en utilisant le flux RSS suivant http://www.lemonde.fr.

L'application, doit afficher l’ensemble des news à la une avec au minimum le titre de la news et l’image associée à la news. En cliquant sur une news il doit être possible de voir le détail de cette news (le titre, l’image, la description et un lien vers la news complète).

## L'implémentation

![alt text](https://github.com/Quentin22/android-rss-app/blob/master/demo.gif)

### Choix
- Pour le développement de cette application, j'ai choisi de cibler la version Android 7.1 (Nougat) avec une compatibilité minimum jusqu'à la version 6.0 (Marshmallow). Pour un développement en production j'aurais plutôt choisi la version 5.1 (Lollipop) avec une compatibilité minimum jusqu'à la version 4.4 (KitKat) dans le but de cibler le plus d'utilisateur possible.

- Pour la gestion des images, mon choix s'est porté sur la bibliothèque [Picasso](http://square.github.io/picasso/) car elle permet de faire beaucoup de choses intéressantes.
  - Téléchargement de l’image de façon asynchrone depuis une URL
  - Gestion du cache d’une image
  - Réduction de la taille d’une image
  - Insertion de l’image dans une ImageView

- J'ai également choisi d'utiliser deux activités.
  - Activité composée d'une liste d'actualités. Chaque item est composé d'une vue personalisée
  - Activité composée du détail d'une actualité. En reprenant la vue personnalisée utilisée dans la liste de la première activité (pour éviter la redondance de code), la description et le lien de la source de l'article
  
- Pour l'actualisation de la liste d'actualités, j'ai choisis d'utiliser le [Swipe-to-Refresh](https://developer.android.com/training/swipe/add-swipe-interface.html).
  
- Ajout d'une SnackBar pour afficher un message d'erreur à l'utilisateur lorsqu'il est impossible de récupérer le flux RSS (pas de connexion internet, serveur de lemonde.fr indisponible, retour du flux RSS non conforme au format attendu...).

### Problèmes rencontrés

La principale difficulté que j'ai rencontré pour le développement de cette application était sur le parser du flux RSS.

### Améliorations

Voici une liste des améliorations auxquelles j'ai pu penser.

- Vérifier la connexion internet et s'il n'y a pas de connexion, proposer à l'utilisateur de l'activer.

- Il serait intéressant de créer un menu latéral comportant les sections associés aux actualités :
`http://www.lemonde.fr/`**`election-presidentielle-2017`**`/article...
http://www.lemonde.fr/`**`international`**`/article...
http://www.lemonde.fr/`**`police-justice`**`/article...
http://www.lemonde.fr/`**`culture`**`/article...`<br />
Lors de la récupération des actualités il serait possible de modifier les items du menu avec les nouvelles sections associées aux nouvelles actualités.

- La date de publication est disponible dans chaque actualité, il serait intéressant de l'afficher dans la vue de l'actualité en détail
`pubDate>Sun, 05 Mar 2017 19:33:44 +0100</pubDate>`. Il faudrait parser la date pour la rendre plus lisible sur l'application. Soit en mettant le jour et l'heure de publication ou bien en mettant le temps écoulé depuis la publication (Principe des posts Facebook).

- L'utilisation de fragments pourrait également être intéressant avec l'ajout d'une animation lors de l'affichage et de la fermeture du fragment de détail d'une actualité.

- Créer un service qui de manière périodique mettrait à jour les actualités.

- La création d'un widget qui afficherait la dernière actualité du moment.


#### Temps de développemnt : 6h
- Création du repository sur Github
- Création d'un logo pour l'application
- Développement du parser XML pour le flux RSS
- Création d'une activité avec une listview pour afficher les actualités
- Création d'une seconde activité pour les détails d'une actualité à la fois
- Ecriture de la documentation
