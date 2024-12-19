package br.com.desafio.desafio.services.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.desafio.desafio.domain.User;
import br.com.desafio.desafio.repository.UserRepository;
import br.com.desafio.desafio.services.UserService;
import br.com.desafio.desafio.services.exceptions.EmailAlreadyExistsException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UUID createUser(String name, String email) {
        var emailExists = this.userRepository.findByEmail(email);
        
        if(emailExists.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        var user = User.builder()
                    .email(email)
                    .name(name)
                    .build();

        this.userRepository.save(user);
        
        return user.getId();
    }
    
}
