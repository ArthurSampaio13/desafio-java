package br.com.desafio.desafio.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UUID createUser(String name, String email);
}
