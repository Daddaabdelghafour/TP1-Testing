package org.example.currency;
import java.util.Scanner;


/**
 * Classe principale pour l'interaction utilisateur
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CurrencyConverter converter = new CurrencyConverter();

        System.out.println("=== Convertisseur de Devises MAD <-> EUR ===");
        System.out.println("Taux actuel: 1 MAD = " + ExchangeRate.getMadToEurRate() + " EUR");
        System.out.println("Taux actuel: 1 EUR = " + ExchangeRate.getEurToMadRate() + " MAD");

        while (true) {
            System. out.println("\n1. MAD -> EUR");
            System. out.println("2. EUR -> MAD");
            System.out.println("3. Quitter");
            System.out.print("Votre choix: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Montant en MAD: ");
                    double madAmount = scanner. nextDouble();
                    try {
                        double eurResult = converter.convertMadToEur(madAmount);
                        System.out.printf("%.2f MAD = %. 2f EUR%n", madAmount, eurResult);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erreur: " + e. getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Montant en EUR: ");
                    double eurAmount = scanner.nextDouble();
                    try {
                        double madResult = converter. convertEurToMad(eurAmount);
                        System.out.printf("%.2f EUR = %.2f MAD%n", eurAmount, madResult);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erreur: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Au revoir!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Choix invalide!");
            }
        }
    }
}