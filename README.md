# ALauncher
Developpement of Asilux Launcher.


# !NEW! PatchLoader

Le `PatchLoader` permet de lire un fichier `.xml` ou d'un autre format, afin de le retranscrire dans le code pour faire une liste des modifications. Voici la structure du `PatchLoader`:


_**Format**_:
- Un titre `Update Name`.
- Des Catégories pour chaque  paragraphe.
- Des paragraphes qui ne sont qu'une liste de modification, d'ajout ou de suppression d'éléments.

_**Catégories**_:
- Les événements.
- Les nouveautés.
- Les modifications majeurs (Refont...).
- Les Fix de bug et de lag.
- Les éléments retirés.

À chaque démarrage du launcher, un processus interne se lance afin de construire une version texte du patch. Si vous n'êtes pas connecté à internet au moment du démarrage du launcher, le `PatchLoader`renvoie un texte vide.

# Nouvelle onglet: Mise à jour

L'onglet des Mise à jour est disponible ! Il permet d'avoir un aperçu du patch dans sa totalité via un super design. Il utilise le `PatchLoader` et donc vous l'auriez compris, pour obtenir cette aperçu il faut être connecté à internet au lancement du ALauncher.

# Patch note du ALauncher | 20/11/2021

_**Nouveauté**_:
- `PatchLoader` | Permet de charger un patch dans une version texte.
- Onglet Mise à jour | Affiche le patch de la dernière mise à jour connu.
- Font Salewk | Utilisation d'une nouvelle police d'écriture.
- Nouveau système de gestion des fichiers | Gestion des fichiers via un `GameDir`.

_**Modification**_:
- Page de connection | Refont du design général de la page.
- Onglet Accueil | Nouvelle animation sur le bouton jouer;

_**Fixs**_:
- Bug photo de profil | Correction de la photo de profil qui ne s'affichais pas par moment.
- Solution lancement de Minecraft | Il faut que vous ayez installé Java 8 afin d'utiliser le launcher.
- Fix animation de la bar de navigation | Amélioration de la qualité des animations.

_**Retiré**_:
- Font Cocogoose supprimé du dossier ressources.
- Ancien système de gestion des fichiers.
