package br.com.desafio.desafio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.desafio.controller.dto.CreateUserRequestDTO;
import br.com.desafio.desafio.controller.dto.CreateUserResponseDTO;
import br.com.desafio.desafio.controller.dto.GetUserByIDResponseDTO;
import br.com.desafio.desafio.controller.dto.UpdateUserRequestDTO;
import br.com.desafio.desafio.controller.dto.UserDTO;
import br.com.desafio.desafio.domain.User;
import br.com.desafio.desafio.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



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

    @GetMapping("/{userId}")
    public ResponseEntity<GetUserByIDResponseDTO> getUserByID(
        @PathVariable UUID userId) {
        UserDTO user = userService.getUserByID(userId);
        var response = new GetUserByIDResponseDTO(
            user.id(),
            user.name(),
            user.email()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserByID(
        @PathVariable UUID userId) {
        userService.deleteUserByID(userId);
        return ResponseEntity.noContent().build();
    }

}
