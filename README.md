# SAÉ 2.03 - Equipe 6

* Gabriel Roche A2

## Présentation

Ce projet a pour but de créer un chat multi-utilisateurs en java.

## Prérequis

* Docker
* Git
* Java

## Création de l'image

- Clonez le répertoire courant à l'aide de la commande suivante :
```bash
git clone git@github.com:TheCrafteur2015/docker-sae203.git
```
- Placez vous dans le répertoire git
```bash
cd docker-sae203
```
- Créez l'image avec la commande suivante :
```bash
docker build -t <nom_de_l_image> 
```
- Puis lancez là
```bash
docker run -p 9001:9001 <nom_de_l_image>
```

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
