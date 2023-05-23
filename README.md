# SAÉ 2.03 - Equipe 6

* Gabriel ROCHE - A2

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

- Téléchargez le fichier .jar : [ChatClient](https://github.com/TheCrafteur2015/docker-sae203/releases/tag/Client)
- Exécutez le à l'aide de la commande suivante :
```bash
java -jar ChatClient.jar
```
- Vous devriez apercevoir cette fenêtre :

![image](https://github.com/TheCrafteur2015/docker-sae203/assets/58007069/48dc4b28-67ff-4d39-baf0-39252328967d)
- Vous pouvez choisir de vous connecter sur l'hôte local où sur une adresse personnalisée :

![image](https://github.com/TheCrafteur2015/docker-sae203/assets/58007069/5d6dbf04-7e17-422a-b60b-159cc547ed14)
- Cliquez sur *Se connecter* pour démarrer la session !
