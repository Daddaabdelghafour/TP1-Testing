package org.example.library;

import java.util.Objects;

public class Livre {
    private String titre;
    private String auteur;
    private int anneePublication;
    private String isbn;

    public Livre(String titre, String auteur, int anneePublication, String isbn) {
        // Validation demandée par la question 4
        if (anneePublication < 0) {
            throw new IllegalArgumentException("L'année de publication ne peut pas être négative");
        }
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.isbn = isbn;
    }

    // Getters
    public String getTitre() { return titre; }
    public String getAuteur() { return auteur; }
    public int getAnneePublication() { return anneePublication; }
    public String getIsbn() { return isbn; }

    // Setters demandés par la question 2
    public void setTitre(String titre) { this.titre = titre; }
    public void setAuteur(String auteur) { this.auteur = auteur; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public void setAnneePublication(int anneePublication) {
        if (anneePublication < 0) {
            throw new IllegalArgumentException("L'année de publication ne peut pas être négative");
        }
        this.anneePublication = anneePublication;
    }

    // Méthodes equals/hashCode demandées par la question 3
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livre livre = (Livre) o;
        return anneePublication == livre.anneePublication &&
                Objects.equals(titre, livre.titre) &&
                Objects.equals(auteur, livre.auteur) &&
                Objects.equals(isbn, livre.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titre, auteur, anneePublication, isbn);
    }
}