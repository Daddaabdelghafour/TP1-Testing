FROM maven:3.9.6-eclipse-temurin-17-alpine

# Installation des outils utiles
RUN apk add --no-cache git vim

# Création du répertoire de travail
WORKDIR /app

# Copie du fichier pom.xml en premier (pour optimiser le cache Docker)
COPY pom.xml .

# Téléchargement des dépendances (mise en cache)
RUN mvn dependency:go-offline -B

# Copie du code source
COPY .  .

# Commande par défaut
CMD ["mvn", "clean", "test"]