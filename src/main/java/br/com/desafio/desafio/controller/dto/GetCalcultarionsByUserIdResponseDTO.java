package br.com.desafio.desafio.controller.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetCalcultarionsByUserIdResponseDTO(
    @Schema(description = "List of unique digits")
    List<UniqueDigitDTO> uniqueDigits
) {
    
}


