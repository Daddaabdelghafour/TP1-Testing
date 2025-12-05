package org.example.exercice3;

import java.time.LocalDate;
import java.util.Objects;

public class Livre {
    private String titre;
    private String auteur;
    private LocalDate datePublication; // Changement de int vers LocalDate
    private String isbn;

    // Constructeur principal (Nouvelle version avec LocalDate)
    public Livre(String titre, String auteur, LocalDate datePublication, String isbn) {
        validerDate(datePublication); // Validation demandée

        // Autres validations basiques
        if (titre == null || titre.trim().isEmpty()) throw new IllegalArgumentException("Titre vide");
        if (auteur == null || auteur.trim().isEmpty()) throw new IllegalArgumentException("Auteur vide");

        this.titre = titre;
        this.auteur = auteur;
        this.datePublication = datePublication;
        this.isbn = isbn;
    }

    // Constructeur de compatibilité (Pour supporter l'ancien format int)
    // On suppose par défaut le 1er janvier de l'année donnée
    public Livre(String titre, String auteur, int annee, String isbn) {
        this(titre, auteur, LocalDate.of(annee, 1, 1), isbn);
    }

    // Méthode de validation (Point 2)
    private void validerDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("La date ne peut pas être nulle");
        }
        int annee = date.getYear();
        int anneeActuelle = LocalDate.now().getYear();

        if (annee < 1000 || annee > anneeActuelle) {
            throw new IllegalArgumentException(
                    "L'année de publication doit être comprise entre 1000 et " + anneeActuelle
            );
        }
    }

    // Getters
    public String getTitre() { return titre; }
    public String getAuteur() { return auteur; }
    public LocalDate getDatePublication() { return datePublication; }
    public String getIsbn() { return isbn; }

    // Getter de compatibilité (retourne l'année)
    public int getAnneePublication() {
        return datePublication.getYear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livre livre = (Livre) o;
        return Objects.equals(datePublication, livre.datePublication) &&
                Objects.equals(titre, livre.titre) &&
                Objects.equals(auteur, livre.auteur) &&
                Objects.equals(isbn, livre.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titre, auteur, datePublication, isbn);
    }
}