package org.example.currency;
/**
 * Classe de conversion de devises entre MAD et EUR
 */
public class CurrencyConverter {

    /**
     * Convertit des MAD en EUR
     * @param amount montant en MAD
     * @return montant en EUR
     * @throws IllegalArgumentException si le montant est négatif
     */
    public double convertMadToEur(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Le montant ne peut pas être négatif");
        }
        return amount * ExchangeRate.getMadToEurRate();
    }

    /**
     * Convertit des EUR en MAD
     * @param amount montant en EUR
     * @return montant en MAD
     * @throws IllegalArgumentException si le montant est négatif
     */
    public double convertEurToMad(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Le montant ne peut pas être négatif");
        }
        return amount * ExchangeRate.getEurToMadRate();
    }
}