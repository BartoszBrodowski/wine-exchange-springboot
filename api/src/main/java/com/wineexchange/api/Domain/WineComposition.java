package com.wineexchange.api.Domain;

import jakarta.persistence.*;
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
    private String alcohol;
}