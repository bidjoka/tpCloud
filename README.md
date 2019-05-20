#Projet Cloud : 
Réalisation d'un site de pétition en ligne TinyPet

#Réalisé par : 
	Sarah ARKAM
	Alexandre BIDJOKA
	Floriane GOUSSIN

Date : 20 mai 2019

	La réalisation de Tiny Pet a été réalisé grâce au framework js Mithrill pour la partie interface, avec l'ajout du framework css bootstrap afin de rendre le tout plus agréable.
	Pour la gestion de notre API, on a utiliser google cloud endpoints. notamment les annotations, @API et @ApiMethod
	Pour rendre notre API en API REST, nous avons utiliser le framework objectify, cet ORM nous a permis notament de transformer les données issu en JSON du datastore en POJO java grâce à l'annotation @entity, de pourvoir profiter du cache grâce à l'annotation @cache, d'indexer nos propriétés grâce aux annotations @index et @id.


#Installation
Pour installer le projet, il faut avoir accès à Google App Engine, notre code source présent sur GitHub et une interface Rest.
Voici les liens permettant d'avoir tous les accès.

	Url App engine permettant d'utiliser l'application est : https://my-cloud-project-232813.appspot.com
	Url github permettant de télécharger le code source est : https://github.com/bidjoka/tpCloud
	Url permettant d'explorer l'interface REST est : https://my-cloud-project-232813.appspot.com/_ah/api/myApi/v1/


#Exploration de l'API
Pour explorer l'API, il faut la lancer le code sur la barre d'adresse d'un navigateur. 
https://my-cloud-project-232813.appstpot.com/_ah/api/explorer

#Description du site de pétition

	Une page d'acceuil s'affiche avec un des liens redirigeant vers les différentes pages implémentées.
		- La liste des fonctionnalités
		- La création d'une pétition
		- La connexion au compte utilisateur

	Une page de connexion. Elle permet à l'utilisateur de se connecter, surtout dans le cas de la création d'une pétition.
	Ceci à l'aide d'un compte google.

	Une page de création de pétition.
		- Soumission d'une petition avec un titre et une description
		- Une possibilité de revenir soit à l'écran d'accueil soit à la liste des fonctionnalités

	Une page d'index qui répertorie les fonctionnalités du site, et qui permet à l'aide de lien d'être directement redirigé vers les pages souhaitées.


L'accès a toutes les pétitions a lieu avec l'adresse suivante : 
https://my-cloud-project-232813.appspot.com/_ah/api/myApi/v1/petitions

#Difficulté

Le site n'est pour le moment pas totale fonctionnel car nous n'avons pas encore mis en relation le back et le font
