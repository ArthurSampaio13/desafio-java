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
}
