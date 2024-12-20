package br.com.desafio.desafio.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.desafio.desafio.controller.dto.CreateUserRequestDTO;
import br.com.desafio.desafio.controller.dto.UpdateUserRequestDTO;
import br.com.desafio.desafio.controller.dto.UserDTO;
import br.com.desafio.desafio.services.UserService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {

    private UserController userController;
    @Mock private UserService userService;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.userController = new UserController(this.userService);
    }

    @BeforeEach
    void resetMocks() {
        reset(this.userService);
    }

    @Test
    void itShouldCreateUser() {
        // Given
        var request = new CreateUserRequestDTO("John Doe", "John.doe@gmail.com");
        var uuid = UUID.randomUUID();
        when(this.userService.createUser(request.name(), request.email()))
        .thenReturn(uuid);
        // When
        var response = this.userController.createUser(request);
        var body = response.getBody();
        // Then
        assertEquals(body.id(), uuid);
        verify(this.userService, times(1))
        .createUser(request.name(), request.email());
        
    }

    @Test
    void itShouldGetUserByID() {
        // Given
        var uuid = UUID.randomUUID();
        var userDTO = new UserDTO(uuid, "John Doe", "john.doe@gmail.com");
        when(this.userService.getUserByID(uuid))
        .thenReturn(userDTO);
        // When
        var response = this.userController.getUserByID(uuid);
        var body = response.getBody();
        // Then
        assertEquals(body.id(), userDTO.id());
        assertEquals(body.name(), userDTO.name());
        assertEquals(body.email(), userDTO.email());
    }

    @Test
    void itShouldDeleteUserById() {
        // Given
        var uuid = UUID.randomUUID();
        
        // When
        this.userController.deleteUserByID(uuid);
        
        // Then
        verify(this.userService, times(1))
        .deleteUserByID(uuid);
    }

    @Test
    void itShouldUpdateUserById() {
        // Given
        var uuid = UUID.randomUUID();
        var request = new UpdateUserRequestDTO("John Doe", "john.doe@gmail.com");
        // When
        this.userController.updateUserByID(uuid, request);
        // Then
        verify(this.userService, times(1))
        .updateUserByID(uuid, request.name(), request.email());
    }
        
}
