package br.com.desafio.desafio.controller.dto;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculateUniqueDigitRequestDTOTest {

    @Test
    void itShouldReturnBigInteger() {
        // Given
        var request = new CalculateUniqueDigitRequestDTO("9875", 1, Optional.empty());
        
        // When
        var result = request.getBigIntegerNumber();
        
        // Then
        assertTrue(result.equals(new BigInteger("9875")));
    }

    @Test
    void itShouldReturnErrorIfNumberIsNotParseableToBigInteger() {
        // Given
        var request = new CalculateUniqueDigitRequestDTO("asd", 1, Optional.empty());

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> request.getBigIntegerNumber());
    }

    @Test
    void itShouldReturnBigIntegerDoesntRespectTheRange() {
        // Given
        var bigInteger = BigInteger.TEN.pow(1000000).add(BigInteger.ONE);
        var request = new CalculateUniqueDigitRequestDTO(bigInteger.toString(), 1, Optional.empty());

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> request.getBigIntegerNumber());
    }

    @Test
    void itShouldThrowAnErrorIfIntegerDoesntRespectTheRange() {
        // Given
        var k = (int) Math.pow(10, 5) + 1;
        var request = new CalculateUniqueDigitRequestDTO("9875", k, Optional.empty());

        // When & Then
        assertThrows(IllegalArgumentException.class, 
        () -> request.getK());
    }

    @Test
    void itShouldThrowAnErrorIfIntegerIsLessThanZero() {
        // Given
        var request = new CalculateUniqueDigitRequestDTO("9875", -1, Optional.empty());
        
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> request.getK());
    }

}
