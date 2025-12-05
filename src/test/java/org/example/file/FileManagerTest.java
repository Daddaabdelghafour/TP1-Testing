package org.example.file;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Exercice 5 : Tests de gestion de fichiers")
class FileManagerTest {

    private final FileManager fileManager = new FileManager();

    // @TempDir crée un dossier temporaire réel sur le disque qui sera détruit après le test
    @TempDir
    Path tempDir;

    @Test
    @DisplayName("Lecture réussie d'un fichier existant")
    void testLectureFichierExistant() throws IOException {
        // 1. Préparation : Créer un vrai fichier dans le dossier temporaire
        Path fichierTest = tempDir.resolve("test.txt");
        String contenuAttendu = "Bonjour, ceci est un test JUnit !";
        Files.writeString(fichierTest, contenuAttendu);

        // 2. Exécution
        String contenuReel = fileManager.readFile(fichierTest.toString());

        // 3. Vérification
        assertEquals(contenuAttendu, contenuReel, "Le contenu lu doit correspondre au fichier écrit");
    }

    @Test
    @DisplayName("Exception IOException si le fichier n'existe pas")
    void testFichierInexistant() {
        // 1. Préparation : Définir un chemin bidon
        String cheminInexistant = "dossier/inexistant/fantome.txt";

        // 2. Exécution & Vérification
        // On s'attend à ce que readFile lance une IOException
        assertThrows(IOException.class, () -> {
            fileManager.readFile(cheminInexistant);
        }, "Une IOException devrait être levée pour un fichier manquant");
    }

    @Test
    @DisplayName("Exception IllegalArgument si chemin vide")
    void testCheminVide() {
        assertThrows(IllegalArgumentException.class, () -> fileManager.readFile(""));
        assertThrows(IllegalArgumentException.class, () -> fileManager.readFile(null));
    }
}