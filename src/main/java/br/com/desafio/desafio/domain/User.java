package br.com.desafio.desafio.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Builder.Default
    @Column(name = "id", 
    nullable = false, 
    updatable = false,
    columnDefinition = "VARCHAR(36)")
    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "name", 
    nullable = false, 
    columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "email", 
    nullable = false,
    columnDefinition = "VARCHAR(255)",
    unique = true)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private List<UniqueDigit> uniqueDigits = new ArrayList<>();
}
