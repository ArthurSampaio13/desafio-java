package br.com.desafio.desafio.controller.dto;

import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

public record CalculateUniqueDigitRequestDTO(
    @Schema(description = "Number to calculate the unique digit", 
    example = "9875", 
    required = true)
    String number,

    @Schema(description = "Number of times the number will be repeated", 
    nullable = true)
    Integer k,

    @Schema(description = "User ID", 
    example = "123e4567-e89b-12d3-a456-426614174000")
    Optional<UUID> userId
) {
    public BigInteger getBigIntegerNumber() {
        final String message_error = "Number must be a positive integer or less than 10^1000000";

        BigInteger bigInteger;

        try {
            bigInteger = new BigInteger(this.number());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Number must be a valid integer");
        }

        if (bigInteger.compareTo(BigInteger.ONE) < 0 
        || bigInteger.compareTo(BigInteger.TEN.pow(1000000)) > 0) {
            throw new IllegalArgumentException(message_error);
        }

        return bigInteger;

    }

    public int getK() {
        final String message = "K must be a positive integer or less than 1000";

        if (this.k() == null) {
            return 1;
        }

        if (!(this.k() >= 1 && this.k() <= Math.pow(10, 5))) {
            throw new IllegalArgumentException(message);
        }

        return this.k();
    }
}
