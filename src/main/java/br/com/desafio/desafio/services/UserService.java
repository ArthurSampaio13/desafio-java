package br.com.desafio.desafio.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.desafio.desafio.controller.dto.UserDTO;

@Service
public interface UserService {
    UUID createUser(String name, String email);
    UserDTO getUserByID(UUID userId);
    void deleteUserByID(UUID userId);
    void updateUserByID(UUID userId, String name, String email);
}
