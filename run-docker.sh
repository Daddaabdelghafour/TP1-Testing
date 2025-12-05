#!/bin/bash

echo "🚀 Lancement du TP JUnit avec Docker"

# Construction des images
echo "📦 Construction de l'image Docker..."
docker-compose build

# Lancement des tests
echo "🧪 Exécution des tests..."
docker-compose run --rm test-runner

# Génération des rapports JaCoCo
echo "📊 Génération des rapports de couverture..."
docker-compose run --rm test-runner mvn jacoco:report

echo "✅ Tests terminés!  Vérifiez les rapports dans target/site/jacoco/"

# Garde le conteneur actif pour développement
echo "🔧 Lancement du conteneur de développement..."
docker-compose up -d junit-tp

echo "💡 Utilisation:"
echo "  - Pour entrer dans le conteneur: docker exec -it junit-tp-container bash"
echo "  - Pour relancer les tests: docker-compose run --rm test-runner"
echo "  - Pour arrêter: docker-compose down"