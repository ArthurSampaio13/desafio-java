package br.com.desafio.desafio.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import java.math.BigInteger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.desafio.desafio.controller.dto.CalculateUniqueDigitRequestDTO;
import br.com.desafio.desafio.services.CalculteUniqueDigitService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculateUniqueDigitControllerTest {

    private CalculateUniqueDigitController calculateUniqueDigitController;
    @Mock private CalculteUniqueDigitService calculteUniqueDigitService;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.calculateUniqueDigitController = new CalculateUniqueDigitController(this.calculteUniqueDigitService);
    }

    @BeforeEach
    void resetMocks() {
        reset(this.calculteUniqueDigitService);
    }

    @Test
    void testCalculateUniqueDigit() {
        // Given
        var request = new CalculateUniqueDigitRequestDTO("9875", 1, null);
        
        when(this.calculteUniqueDigitService.calculateUniqueDigit(any(BigInteger.class), anyInt(),any())
        ).thenReturn(8);

        // When
        var response = this.calculateUniqueDigitController.calculateUniqueDigit(request);
        var body = response.getBody();

        // Then
        assertEquals(8, body);
        verify(this.calculteUniqueDigitService, times(1))
        .calculateUniqueDigit(any(BigInteger.class), anyInt(), any());
    }
}
