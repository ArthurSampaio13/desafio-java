package br.com.desafio.desafio.services.impl;

import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.desafio.desafio.domain.UniqueDigit;
import br.com.desafio.desafio.services.CalculteUniqueDigitService;

@Service
public class CalculateUniqueDigitServiceImpl implements CalculteUniqueDigitService{

    @Override
    public Integer calculateUniqueDigit(BigInteger number, int k, Optional<UUID> userId) {
        var uniqueDigit = new UniqueDigit(number, k);
        return uniqueDigit.getResult();
    }
    
}
