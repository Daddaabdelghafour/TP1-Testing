package org.example.exercice3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Exercice 3 : Tests avec LocalDate et Validation")
class LivreTest {

    // 1 & 3. Test avec LocalDate valide
    @Test
    @DisplayName("Création avec une LocalDate valide")
    void testCreationDateValide() {
        LocalDate date = LocalDate.of(2000, 5, 20);
        Livre livre = new Livre("Dune", "Herbert", date, "123");

        assertEquals(date, livre.getDatePublication());
    }

    // 2 & 7. Test Exception (Date trop ancienne)
    @Test
    @DisplayName("Erreur si année < 1000")
    void testDateTropAncienne() {
        LocalDate dateAncienne = LocalDate.of(999, 12, 31);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Livre("Test", "Auteur", dateAncienne, "ISBN");
        });

        assertTrue(exception.getMessage().contains("entre 1000"));
    }

    // 2 & 7. Test Exception (Date future)
    @Test
    @DisplayName("Erreur si année > année actuelle")
    void testDateFuture() {
        int anneeProchaine = LocalDate.now().getYear() + 1;
        LocalDate dateFuture = LocalDate.of(anneeProchaine, 1, 1);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Livre("Test", "Auteur", dateFuture, "ISBN");
        });

        assertTrue(exception.getMessage().contains("entre 1000"));
    }

    // 4. Compatibilité avec l'ancien format (int)
    @Test
    @DisplayName("Compatibilité constructeur int (Ex2)")
    void testCompatibiliteAncienConstructeur() {
        // On utilise le constructeur qui prend un int (1995)
        Livre livre = new Livre("Java", "Gosling", 1995, "ISBN");

        // On vérifie que ça a bien créé une LocalDate au 1er Janvier 1995
        assertEquals(LocalDate.of(1995, 1, 1), livre.getDatePublication());
        assertEquals(1995, livre.getAnneePublication());
    }

    // 5. Performance
    @Test
    @DisplayName("Performance avec LocalDate")
    void testPerformance() {
        assertTimeout(Duration.ofMillis(200), () -> {
            for (int i = 0; i < 10000; i++) {
                // Utilisation de dates dynamiques
                new Livre("T", "A", LocalDate.of(1000 + (i % 1000), 1, 1), "I");
            }
        });
    }

    // 8. Cas limites (Bornes exactes)
    @Test
    @DisplayName("Cas limites : An 1000 et Année Actuelle")
    void testBornesExactes() {
        // Borne inférieure exacte (An 1000) -> Doit passer
        assertDoesNotThrow(() -> {
            new Livre("Livre", "Auteur", LocalDate.of(1000, 1, 1), "ISBN");
        });

        // Borne supérieure exacte (Aujourd'hui) -> Doit passer
        assertDoesNotThrow(() -> {
            new Livre("Livre", "Auteur", LocalDate.now(), "ISBN");
        });
    }
}