package org.example.currency;

/**
 * Classe de gestion des taux de change entre MAD et EUR
 */
public class ExchangeRate {
    private static double madToEurRate = 0.09;
    private static double eurToMadRate = 11.11;

    public static double getMadToEurRate() {
        return madToEurRate;
    }

    public static double getEurToMadRate() {
        return eurToMadRate;
    }

    public static void setMadToEurRate(double rate) {
        if (rate <= 0) {
            throw new IllegalArgumentException("Le taux de change doit être positif");
        }
        madToEurRate = rate;
        eurToMadRate = 1.0 / rate;
    }

    public static void setEurToMadRate(double rate) {
        if (rate <= 0) {
            throw new IllegalArgumentException("Le taux de change doit être positif");
        }
        eurToMadRate = rate;

        madToEurRate = 1.0 / rate;
    }
}