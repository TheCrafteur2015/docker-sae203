# SAÉ 2.03 - Equipe 6

* Gabriel Roche A2

## Présentation

Ce projet a pour but de créer un chat multi-utilisateurs en java.

## Prérequis

* Docker
* Git
* Java

## Création de l'image

- Clonez le répertoire courant à l'aide de la commande suivante
```bash
git clone git@github.com:TheCrafteur2015/docker-sae203.git
```
- Placez vous dans le répertoire git
```bash
cd docker-sae203
```
- Créez l'image avec la commande suivante
```bash
docker build -t <nom_de_l_image> 
```
- Puis lancez là
```bash
docker run -p 9001:9001 <nom_de_l_image>
```

## Utilisation du client

- Téléchargez le fichier .class : [ChatClient](https://github.com/TheCrafteur2015/docker-sae203/releases/tag/Client)
