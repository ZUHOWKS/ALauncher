# ALauncher
Developpement of Asilux Launcher.


# PatchLoader

Le `PatchLoader` permet de lire un fichier `.xml` ou d'un autre format, afin de le retranscrire dans le code pour faire une liste des modifications.


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

_**Tâche à réaliser**_:
- Déterminer un format pour le fichier `patch` | Par défaut le format sera `.xml`.
- Création d'une structure des informations dans le fichier `patch.xml`.
- Utilisation du RSS afin de lire le fichier `patch.xml`
