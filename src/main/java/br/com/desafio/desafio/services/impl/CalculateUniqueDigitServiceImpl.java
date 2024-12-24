package br.com.desafio.desafio.services.impl;

import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.desafio.desafio.domain.UniqueDigit;
import br.com.desafio.desafio.repository.UserRepository;
import br.com.desafio.desafio.services.CalculteUniqueDigitService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalculateUniqueDigitServiceImpl implements CalculteUniqueDigitService{

    private final UserRepository userRepository;

    @Override
    public Integer calculateUniqueDigit(BigInteger number, int k, Optional<UUID> userId) {
        var uniqueDigit = new UniqueDigit(number, k);

        if (userId.isPresent()) {
            this.userRepository.findById(userId.get()).ifPresent(user -> { 
                user.addUniqueDigit(uniqueDigit);
                this.userRepository.save(user);
            });
        }

        return uniqueDigit.getResult();
    }
    
}
