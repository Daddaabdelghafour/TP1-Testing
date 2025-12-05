# TP1 - Ingénierie Logicielle : Tests Unitaires & Qualité du Code

École Nationale des Sciences Appliquées de Marrakech (ENSAM)
Module :** Ingénierie Logicielle  
Filière : Génie Informatique

Ce dépôt contient la réalisation des travaux pratiques sur les tests unitaires en Java, l'automatisation avec Maven et l'analyse de couverture de code avec JaCoCo.

## 📋 Description du Projet

L'objectif de ce TP est de maîtriser les concepts fondamentaux du testing en Java :
1.  JUnit 5 : Écriture de tests unitaires, assertions, tests paramétrés.
2.  Maven : Gestion du cycle de vie du projet et des dépendances.
3.  JaCoCo : Analyse de la couverture de code (Code Coverage).
4.  Bonnes pratiques : TDD, gestion des exceptions, validation et encapsulation.

Le projet est conteneurisé avec Docker pour garantir un environnement d'exécution stable et reproductible.

## 🛠️ Technologies Utilisées

*   Java 17 (LTS)
*   Maven 3.9 (Build & Dependency Management)
*   JUnit 5.11 (Framework de test)
*   AssertJ (Assertions fluides)
*   JaCoCo (Rapport de couverture)
*   Docker & Docker Compose (Environnement d'exécution)

## 📂 Structure du Projet

Le code est organisé par exercice dans des packages distincts pour une meilleure lisibilité :

```
src/
├── main/java/org/example/
│   ├── currency/    # Exercice 1 : Convertisseur de devises
│   ├── library/     # Exercice 2 : Gestion de bibliothèque (Base)
│   ├── exercice3/   # Exercice 3 : Gestion avancée (Dates/Refactoring)
│   └── file/        # Exercice 5 : Gestion de fichiers et Exceptions
└── test/java/org/example/
    └── ...          # Tests unitaires correspondants (Miroir du main)
```

## 🚀 Installation et Exécution

### Prérequis
*   Docker Desktop installé.
*   (Optionnel) Java 17 et Maven si exécution locale sans Docker.

### Lancement rapide (Via Docker)

Un script automatisé est fourni pour construire le projet, lancer les tests et générer les rapports.

**Sur Windows (PowerShell/CMD) :**
```powershell
.\run-docker.bat
```

**Sur Linux / Mac (Bash) :**
```bash
chmod +x run-docker.sh
./run-docker.sh
```

Ce script va :
1.  Créer l'image Docker contenant Maven et Java.
2.  Compiler le projet.
3.  Exécuter l'ensemble des tests unitaires.
4.  Générer le rapport de couverture JaCoCo.

## 📊 Consulter les Rapports de Tests

Une fois l'exécution terminée, les rapports sont disponibles dans le dossier `target` :

*   **Rapport JaCoCo (Couverture)** :  
    Ouvrez le fichier suivant dans votre navigateur :  
    `target/site/jacoco/index.html`

## 📝 Détail des Exercices

### Exercice 1 : Convertisseur de Devises
*   Mise en place de JUnit 5.
*   Tests paramétrés (`@ParameterizedTest`) pour tester plusieurs montants.
*   Gestion des exceptions (`IllegalArgumentException`) pour les montants négatifs.
*   Problématique des nombres flottants (`double`) résolue via `AssertJ` (`isCloseTo`).

### Exercice 2 : Gestion de Bibliothèque
*   Création de classes POJO (`Livre`, `Bibliotheque`).
*   Tests d'encapsulation, getters/setters et égalité (`equals/hashCode`).
*   Validation des données dans le constructeur.

### Exercice 3 : Refactoring et Dates
*   Évolution de la classe `Livre` pour utiliser `java.time.LocalDate`.
*   Gestion de la compatibilité ascendante (anciens constructeurs).
*   Validation logique métier (Dates entre l'an 1000 et aujourd'hui).
*   Tests des cas limites (Boundaries).

### Exercice 4 : Couverture de Code (JaCoCo)
*   Configuration du plugin Maven JaCoCo.
*   Analyse des rapports pour identifier le code non testé (branches manquées).
*   Objectif visé : > 80% de couverture sur le cœur métier.

### Exercice 5 : Gestion des Fichiers
*   Manipulation des I/O (`FileManager`).
*   Tests d'intégration fichier temporaires (`@TempDir` JUnit 5).
*   Distinction entre Exceptions Vérifiées (`IOException`) et Non-Vérifiées.

## 👤 Auteur

**Nom :** Dadda Abdelghafour  
**Date :** 5 Décembre 2025  
**GitHub :** @Daddaabdelghafour

---
*Ce projet a été réalisé dans le cadre du module de Tests Logiciels de l'ENSAM.*