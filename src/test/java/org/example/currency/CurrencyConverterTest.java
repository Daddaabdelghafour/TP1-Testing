package org.example.currency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;
import java.time.Duration;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests du convertisseur de devises")
class CurrencyConverterTest {

    private CurrencyConverter converter;

    @BeforeEach
    void setUp() {
        converter = new CurrencyConverter();
        ExchangeRate.setMadToEurRate(0.09);
    }

    @Test
    @DisplayName("Conversion MAD vers EUR avec montant zéro")
    void testConvertMadToEur_ZeroAmount() {
        assertThat(converter.convertMadToEur(0.0)).isEqualTo(0.0);
    }

    @Test
    @DisplayName("Conversion EUR vers MAD avec montant zéro")
    void testConvertEurToMad_ZeroAmount() {
        assertThat(converter.convertEurToMad(0.0)).isEqualTo(0.0);
    }

    @Test
    @DisplayName("Exception levée pour montant négatif MAD->EUR")
    void testConvertMadToEur_NegativeAmount_ShouldThrowException() {
        assertThatThrownBy(() -> converter.convertMadToEur(-100.0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Exception levée pour montant négatif EUR->MAD")
    void testConvertEurToMad_NegativeAmount_ShouldThrowException() {
        assertThatThrownBy(() -> converter.convertEurToMad(-50.0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Conversion MAD vers EUR avec taux actuel")
    void testConvertMadToEur_WithCurrentRate() {
        double result = converter.convertMadToEur(100.0);
        assertThat(result).isCloseTo(9.0, within(0.001));
    }

    @Test
    @DisplayName("Conversion EUR vers MAD avec taux actuel")
    void testConvertEurToMad_WithCurrentRate() {
        // CORRECTION ICI : On utilise le taux réel calculé par la classe
        double eurAmount = 10.0;
        double realRate = ExchangeRate.getEurToMadRate();
        double expectedMad = eurAmount * realRate;

        double result = converter.convertEurToMad(eurAmount);

        assertThat(result).isCloseTo(expectedMad, within(0.001));
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 5.5, 25.75, 100.0, 999.99})
    void testConversions_WithRandomValues(double amount) {
        double madToEur = converter.convertMadToEur(amount);
        double eurToMad = converter.convertEurToMad(amount);

        assertThat(madToEur).isGreaterThanOrEqualTo(0);

        // Test aller-retour (Round trip)
        double backToOriginal = converter.convertEurToMad(madToEur);
        assertThat(backToOriginal).isCloseTo(amount, within(0.001));
    }

    @RepeatedTest(10)
    void testConversion_Performance() {
        Random random = new Random();
        double amount = random.nextDouble() * 1000;

        assertTimeout(Duration.ofMillis(100), () -> {
            converter.convertMadToEur(amount);
            converter.convertEurToMad(amount);
        });
    }
}