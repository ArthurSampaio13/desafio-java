package br.com.desafio.desafio.domain;

import java.math.BigInteger;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "unique_digits")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UniqueDigit {

    @Id
    @Column(name = "id", 
    nullable = false, 
    updatable = false, 
    columnDefinition = "VARCHAR(36)")
    private UUID id = UUID.randomUUID();

    @Column(name = "result", 
    nullable = false,
    columnDefinition = "int")
    private int result;

    @ManyToOne
    @JoinColumn(name = "user_id",
    columnDefinition = "VARCHAR(36)")
    private User user;

    @Column(name = "number", nullable = false, columnDefinition = "TEXT")
    private String number;

    @Column(name = "k", nullable = false, columnDefinition = "int")
    private int k;

    public UniqueDigit(BigInteger number, Integer k) {
        this.result = this.calculateNewUniqueDigit(number, k);
        this.number = String.valueOf(number);
        this.k = k;
    }

    public int getResult() {
        return result;
    }

    private int calculateNewUniqueDigit(BigInteger number, Integer k) {
        String numberString = String.valueOf(number)
        .repeat(k);

        int uniqueDigit = this.sumOfDigits(numberString);

        while(uniqueDigit > 9) {
            uniqueDigit = this.sumOfDigits(String.valueOf(uniqueDigit));
        }

        return uniqueDigit;
    }

    private int sumOfDigits(String numberString) {
        int sum = 0;

        for (var digit : numberString.toCharArray()) {
            sum += Character.getNumericValue(digit);
        }

        return sum;
    }

    public void setUser(User user) {
        this.user = user;
    }
} 
