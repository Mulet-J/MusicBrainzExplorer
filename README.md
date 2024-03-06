# Music Library

## Architecture et Choix de Conception - Résumé Technique

Notre application est un projet permettant de faire des recherches d'artistes, de groupes et de musiques.
Une API externe est utilisée et permet de récupérer différentes informations qui sont présentées à l'utilisateur.

Cette application repose sur le modèle d'architecture MVVM (Model-View-ViewModel) pour garantir une structure organisée et maintenable. Les principaux choix de conception ont été orientés par les exigences spécifiques de l'application.

Nous faisons usage de deux API essentielles. Tout d'abord, l'API de MusicBrainz nous fournit toutes les données nécessaires concernant les artistes,albums,musiques,etc... 
Ensuite, nous avons recours à l'API YouTube de Google pour effectuer des recherches de vidéos et obtenir les liens nécessaires afin de lancer notre lecteur YouTube.

Dans le cadre de ce projet, l'intégration du lecteur YouTube nécessitait une approche spécifique pour offrir une expérience utilisateur fluide et personnalisée. Pour cette raison, nous avons choisi de développer notre propre activité personnalisée pour le lecteur YouTube. Cette décision nous a permis d'avoir un contrôle total sur le comportement du lecteur et de garantir un lancement optimal des clips musicaux, tout en préservant la cohérence de notre architecture MVVM.

Notre classe d'adaptateur est un pilier fondamental de notre architecture, car elle assure l'homogénéité de la présentation à travers tous nos RecyclerView. Grâce à cette uniformisation, un seul modèle de mise en page suffit pour afficher les RecyclerViews dans l'ensemble de notre application, simplifiant ainsi considérablement le processus de développement et de maintenance.

**Découplage des responsabilités :**

L'architecture MVVM offre un découplage clair entre les différentes couches, facilitant la maintenance, les tests, et permettant l'ajout aisé de nouvelles fonctionnalités. La séparation des préoccupations offre une base solide pour un développement évolutif.

**Gestion de l'État :**

MVVM, avec son ViewModel, simplifie la gestion de l'état de l'application. Les données persistantes peuvent être gérées sans effort, améliorant la robustesse et la cohérence de l'application.

**Réactivité :** 

La nature réactive du modèle MVVM permet une mise à jour automatique de l'interface utilisateur en réponse aux changements dans le modèle, améliorant l'expérience utilisateur.

**Extensibilité et Évolutivité :**

L'architecture a été conçue en gardant à l'esprit la possibilité d'ajouter de nouvelles fonctionnalités. La séparation des composants permet l'intégration aisée de nouvelles vues, modèles, ou fonctionnalités sans perturber le reste du système.

**UI et Expérience Utilisateur :**

Un UI simple et épuré a été conçu afin de mettre en valeur l'information. Des RecyclerViews pour afficher les résultats de recherche de manière efficace et dynamique.

**Gestion des Erreurs et État :**

Les erreurs sont catch de manière à s'assurer que l'application fonctionne même en cas de problème de requêtes.


