package org.example.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class LivreTest {

    // 1. Test de création avec valeurs spécifiques
    @Test
    @DisplayName("Q1: Création d'une instance Livre valide")
    void testCreationLivre() {
        Livre livre = new Livre("Le Petit Prince", "Antoine de Saint-Exupéry", 1943, "978-0156012195");

        assertAll("Vérification des propriétés",
                () -> assertEquals("Le Petit Prince", livre.getTitre()),
                () -> assertEquals("Antoine de Saint-Exupéry", livre.getAuteur()),
                () -> assertEquals(1943, livre.getAnneePublication()),
                () -> assertEquals("978-0156012195", livre.getIsbn())
        );
    }

    // 2. Test des Getters et Setters
    @Test
    @DisplayName("Q2: Test des Getters et Setters")
    void testGettersSetters() {
        Livre livre = new Livre("Titre", "Auteur", 2000, "ISBN");

        livre.setTitre("Nouveau Titre");
        assertEquals("Nouveau Titre", livre.getTitre());

        livre.setAuteur("Nouvel Auteur");
        assertEquals("Nouvel Auteur", livre.getAuteur());

        livre.setAnneePublication(2022);
        assertEquals(2022, livre.getAnneePublication());
    }

    // 3. Test d'égalité
    @Test
    @DisplayName("Q3: Deux instances avec mêmes attributs sont égales")
    void testEgalite() {
        Livre livre1 = new Livre("1984", "Orwell", 1949, "111");
        Livre livre2 = new Livre("1984", "Orwell", 1949, "111");
        Livre livre3 = new Livre("Autre", "Orwell", 1949, "111");

        assertEquals(livre1, livre2, "Les livres devraient être égaux");
        assertNotEquals(livre1, livre3, "Les livres devraient être différents");
    }

    // 4. Validation des données (Année négative)
    @Test
    @DisplayName("Q4: Validation - Rejet année négative")
    void testValidationAnnee() {
        // Test via constructeur
        assertThrows(IllegalArgumentException.class, () -> {
            new Livre("Titre", "Auteur", -100, "ISBN");
        });

        // Test via setter
        Livre livre = new Livre("Titre", "Auteur", 2000, "ISBN");
        assertThrows(IllegalArgumentException.class, () -> {
            livre.setAnneePublication(-50);
        });
    }

    // 5. Cas limites (Chaînes vides, années extrêmes)
    @Test
    @DisplayName("Q5: Cas limites")
    void testCasLimites() {
        // Année très ancienne mais valide (>0)
        Livre livreVieux = new Livre("Titre", "Auteur", 1, "ISBN");
        assertEquals(1, livreVieux.getAnneePublication());

        // Chaînes vides (acceptées dans ce code simple, mais on vérifie que ça ne plante pas)
        Livre livreVide = new Livre("", "", 2020, "");
        assertTrue(livreVide.getTitre().isEmpty());
    }

    // 6. Performance
    @Test
    @DisplayName("Q6: Performance création massive")
    void testPerformance() {
        assertTimeout(Duration.ofMillis(200), () -> {
            for (int i = 0; i < 10000; i++) {
                new Livre("Titre" + i, "Auteur", 2000, "ISBN" + i);
            }
        });
    }

    // 7. Interaction avec Bibliothèque
    @Test
    @DisplayName("Q7: Interaction Bibliothèque")
    void testInteractionBibliotheque() {
        Bibliotheque biblio = new Bibliotheque();
        Livre l1 = new Livre("Java", "A", 2020, "1");

        biblio.ajouterLivre(l1);

        assertEquals(l1, biblio.rechercherParTitre("Java"));
        assertNull(biblio.rechercherParTitre("Inexistant"));
    }

    // 8. Encapsulation
    @Test
    @DisplayName("Q8: Vérification Encapsulation (Champs privés)")
    void testEncapsulation() {
        Field[] fields = Livre.class.getDeclaredFields();
        for (Field field : fields) {
            assertTrue(Modifier.isPrivate(field.getModifiers()),
                    "Le champ " + field.getName() + " devrait être privé");
        }
    }

    // 9. Fuite de mémoire (Simulation)
    @Test
    @DisplayName("Q9: Test absence fuite mémoire (GC)")
    void testMemoire() {
        // Ce test vérifie simplement que le GC peut passer sans erreur
        // Un vrai test de fuite nécessite des outils de profiling,
        // mais JUnit vérifie ici que la création/destruction ne crash pas la JVM
        Executable allocation = () -> {
            for(int i=0; i<50000; i++) {
                new Livre("T", "A", 2000, "I");
            }
        };
        assertDoesNotThrow(allocation);
    }
}