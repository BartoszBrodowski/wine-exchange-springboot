package com.wineexchange.api.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "wine_compositions")
public class WineComposition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String grape;

    @NotNull
    @Digits(integer = 2, fraction = 1, message = "Invalid alcohol format")
    private String alcohol;
}