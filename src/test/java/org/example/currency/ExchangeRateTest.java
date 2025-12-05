package org.example.currency;
import org.junit.jupiter. api.BeforeEach;
import org. junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests unitaires pour ExchangeRate
 */
@DisplayName("Tests des taux de change")
class ExchangeRateTest {

    @BeforeEach
    void setUp() {
        // Réinitialiser les taux par défaut
        ExchangeRate.setMadToEurRate(0.09);
    }

    @Test
    @DisplayName("Récupération des taux par défaut")
    void testGetDefaultRates() {
        // When & Then
        assertThat(ExchangeRate.getMadToEurRate()).isCloseTo(0.09, within(0.001));
        assertThat(ExchangeRate.getEurToMadRate()).isCloseTo(11.11, within(0.01));
    }

    @Test
    @DisplayName("Mise à jour du taux MAD vers EUR")
    void testSetMadToEurRate_ValidRate() {
        // Given
        double newRate = 0.10;

        // When
        ExchangeRate.setMadToEurRate(newRate);

        // Then
        assertThat(ExchangeRate.getMadToEurRate()).isEqualTo(newRate);
        assertThat(ExchangeRate.getEurToMadRate()).isCloseTo(10.0, within(0.001));
    }

    @Test
    @DisplayName("Exception pour taux négatif MAD->EUR")
    void testSetMadToEurRate_NegativeRate_ShouldThrowException() {
        // Given
        double negativeRate = -0.05;

        // When & Then
        assertThatThrownBy(() -> ExchangeRate.setMadToEurRate(negativeRate))
                . isInstanceOf(IllegalArgumentException. class)
                .hasMessage("Le taux de change doit être positif");
    }

    @Test
    @DisplayName("Exception pour taux zéro MAD->EUR")
    void testSetMadToEurRate_ZeroRate_ShouldThrowException() {
        // Given
        double zeroRate = 0.0;

        // When & Then
        assertThatThrownBy(() -> ExchangeRate.setMadToEurRate(zeroRate))
                . isInstanceOf(IllegalArgumentException. class)
                .hasMessage("Le taux de change doit être positif");
    }

    @Test
    @DisplayName("Mise à jour du taux EUR vers MAD")
    void testSetEurToMadRate_ValidRate() {
        // Given
        double newRate = 10.0;

        // When
        ExchangeRate. setEurToMadRate(newRate);

        // Then
        assertThat(ExchangeRate.getEurToMadRate()).isEqualTo(newRate);
        assertThat(ExchangeRate.getMadToEurRate()). isCloseTo(0.10, within(0.001));
    }

    @Test
    @DisplayName("Cohérence des taux inverses")
    void testRatesConsistency() {
        // Given
        double madToEurRate = 0.08;

        // When
        ExchangeRate.setMadToEurRate(madToEurRate);

        // Then
        double eurToMadRate = ExchangeRate.getEurToMadRate();
        assertThat(madToEurRate * eurToMadRate).isCloseTo(1.0, within(0.001));
    }
}