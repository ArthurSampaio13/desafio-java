package br.com.desafio.desafio.services;

import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

public interface CalculteUniqueDigitService {
    Integer calculateUniqueDigit(BigInteger number, int k, Optional<UUID> userId);
}
