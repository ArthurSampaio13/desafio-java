package br.com.desafio.desafio.controller.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateUserResponseDTO(
    @Schema(description = "User id")
    UUID id
) {
    
}
