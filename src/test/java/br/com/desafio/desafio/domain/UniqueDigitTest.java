package br.com.desafio.desafio.domain;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigInteger;

import org.junit.jupiter.api.Test;


public class UniqueDigitTest {
    @Test
    void itShouldReturnTheRightValue() {
        // Given
        var number = BigInteger.valueOf(9875);
        var k = 4;
        
        // When
        var uniqueDigit = new UniqueDigit(number, k);
        var anotherUniqueDigit = new UniqueDigit(number, 1);

        // Then
        assertThat(uniqueDigit.getResult()).isEqualTo(8); 
        assertThat(anotherUniqueDigit.getResult()).isEqualTo(2);
    }
}
