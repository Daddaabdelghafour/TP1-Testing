package org.example.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    /**
     * Lit le contenu d'un fichier texte.
     *
     * @param filePath Chemin du fichier à lire.
     * @return Le contenu du fichier sous forme de String.
     * @throws IOException Si le fichier n'existe pas ou ne peut être lu.
     */
    public String readFile(String filePath) throws IOException {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Le chemin du fichier ne peut pas être vide");
        }

        Path path = Paths.get(filePath);

        // La méthode Files.readString (Java 11+) lève automatiquement une IOException
        // (NoSuchFileException) si le fichier n'existe pas.
        return Files.readString(path);
    }
}