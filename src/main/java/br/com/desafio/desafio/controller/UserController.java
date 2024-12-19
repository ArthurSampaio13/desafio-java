package br.com.desafio.desafio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.desafio.controller.dto.CreateUserRequestDTO;
import br.com.desafio.desafio.controller.dto.CreateUserResponseDTO;
import br.com.desafio.desafio.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<CreateUserResponseDTO> createUser(
        @RequestBody @Valid CreateUserRequestDTO createUserRequestDTO) {
        UUID userId = userService.createUser(createUserRequestDTO.name(), 
                                            createUserRequestDTO.email());
        var response = new CreateUserResponseDTO(userId);
        return ResponseEntity.ok(response);
    }
    
}
