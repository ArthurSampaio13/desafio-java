package br.com.desafio.desafio.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.desafio.desafio.domain.User;
import br.com.desafio.desafio.repository.UserRepository;
import br.com.desafio.desafio.services.exceptions.EmailAlreadyExistsException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceImplTest {

    private UserServiceImpl userServiceImpl;
    @Mock private UserRepository userRepository;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.userServiceImpl = new UserServiceImpl(userRepository);
    }

    @BeforeEach
    void resetMocks() {
        reset(this.userRepository);
    }

    @Test
    void itShouldCreateAnUserAndSaveOnRepository() {
        // Given
        var email = "joh.doe@gmail.com";
        var name = "John Doe";
        
        when(this.userRepository.findByEmail(email))
        .thenReturn(Optional.empty());
        // When
    
        this.userServiceImpl.createUser(name, email);
        
        // Then
        var captor = ArgumentCaptor.forClass(User.class);
        verify(this.userRepository, times(1))
        .save(captor.capture());

        var user = captor.getValue();
        assertEquals(email, user.getEmail());
        assertEquals(name, user.getName());
    }

    @Test
    void itShouldThrowAnExceptionWhenEmailAlreadyExists() {
        // Given
        var email = "joh.doe@gmail.com";
        var name = "John Doe";

        when(this.userRepository.findByEmail(email))
        .thenReturn(Optional.of(User.builder().build()));
        
        // When & Then
        assertThrows(EmailAlreadyExistsException.class, () -> {
            this.userServiceImpl.createUser(name, email);
        });
        
        verify(this.userRepository, times(0))
        .save(any());
    }
}
