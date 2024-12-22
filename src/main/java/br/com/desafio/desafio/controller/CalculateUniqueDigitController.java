package br.com.desafio.desafio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.desafio.controller.dto.CalculateUniqueDigitRequestDTO;
import br.com.desafio.desafio.services.CalculteUniqueDigitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/calculate")
@RequiredArgsConstructor
public class CalculateUniqueDigitController {

    private final CalculteUniqueDigitService calculteUniqueDigitService;

    @PostMapping
    public ResponseEntity<Integer> calculateUniqueDigit(
        @RequestBody @Valid CalculateUniqueDigitRequestDTO calculateUniqueDigitRequestDTO
    ) {        
        var response = this.calculteUniqueDigitService.calculateUniqueDigit(
            calculateUniqueDigitRequestDTO.getBigIntegerNumber(), 
            calculateUniqueDigitRequestDTO.getK(),
            calculateUniqueDigitRequestDTO.userId()
            );

        return ResponseEntity.ok(response);
    }
}
