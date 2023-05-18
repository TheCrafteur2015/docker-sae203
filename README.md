# SAÉ 2.03 Equipe 6

* Gabriel Roche A2

## Présentation

* Pour ce projet nous avons décider de faire un container docker qui permet de lancer un serveur web ayant pour but de créer un serveur de dépot de stockage de fichiers.
* Ce container est basé sur une image de base debian et est construit à partir d'un DockerFile.

## Prérequis pour lancer le container 

* Avoir docker d'installer sur sa machine
* Avoir git d'installer sur sa machine
* `(Facultatif)` Avoir java d'installer sur sa machine

## Lancement du container

* Cloner le repository git à l'aide de la commande suivante : <b>git clone git@github.com:IKLSI/docker-sae-203-equipe12-depot</b>
* Se placer dans le dossier docker-sae-203-equipe12-depot puis dans le dossier src
* Lancer la commande suivante : <b>docker build -t nom_du_container -f DockerFile chemin/absolu</b>
* Puis la commande suivante : <b>docker run -p 3000:3000 -v $(pwd)/uploads:/docker/uploads nom_du_container</b> sous Linux
* Sous Windows <b>docker run -p 3000:3000 -v chemin_absolu/uploads:/docker/uploads nom_du_container</b>
* Enfin, ouvrir un navigateur et taper l'adresse suivante : <b>localhost:3000</b>

## Autres projets 

* `gh-pages` : <a href="https://iklsi.github.io/docker-sae-203-equipe12/">Lien</a>
* `chat` : <a href="https://github.com/IKLSI/docker-sae-203-equipe12-chat">Lien</a>
* `vscode` : <a href="https://github.com/IKLSI/docker-sae-203-equipe12-vscode">Lien</a>
