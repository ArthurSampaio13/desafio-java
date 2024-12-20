package br.com.desafio.desafio.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateUserRequestDTO(
    @Schema(description = "User name", example = "John Doe")
    String name,
    @Schema(description = "User email", example = "john.doe@gmail.com")
    String email
) {
    
}
