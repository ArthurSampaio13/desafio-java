package br.com.desafio.desafio.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.desafio.desafio.controller.dto.UniqueDigitDTO;
import br.com.desafio.desafio.controller.dto.UserDTO;
import br.com.desafio.desafio.domain.User;
import br.com.desafio.desafio.repository.UserRepository;
import br.com.desafio.desafio.services.UserService;
import br.com.desafio.desafio.services.exceptions.EmailAlreadyExistsException;
import br.com.desafio.desafio.services.exceptions.UserNotFoundException;
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

    @Override
    public UserDTO getUserByID(UUID userId) {
        var user = this.userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException());

        return new UserDTO(user.getId(), 
        user.getName(), 
        user.getEmail()
        );

    }

    @Override
    public void deleteUserByID(UUID userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public void updateUserByID(UUID userId, String name, String email) {
        var user = this.userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException());
        user.setName(name);
        user.setEmail(email);

        this.userRepository.save(user);
    }

    @Override
    public List<UniqueDigitDTO> getCalculationsByUserId(UUID userId) {
        var user = this.userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException());

        return user.getUniqueDigits()
        .stream()
        .map(uniqueDigit -> {
            return new UniqueDigitDTO(
                uniqueDigit.getResult(),
                uniqueDigit.getNumber(),
                uniqueDigit.getK()
            );
        }).collect(Collectors.toList());
        
    }
    
}
